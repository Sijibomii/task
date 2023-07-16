defmodule Websocket.SocketHandler do
  import WebsocketHandler.Utils.Version
  @behaviour :cowboy_websocket
  ## initialization boilerplate

  defstruct user: nil,
            ip: nil,
            encoding: nil,
            compression: nil,
            version: nil,
            callers: []

  @type state :: %__MODULE__{
          user: nil | Model.Schemas.User.t(),
          ip: String.t(),
          encoding: :etf | :json,
          compression: nil | :zlib,
          version: Version.t(),
          callers: [pid]
        }
  @type json :: String.t() | number | boolean | nil | [json] | %{String.t() => json}

  @impl true
  def init(request, _state) do
    props = :cowboy_req.parse_qs(request)

    compression =
      case :proplists.get_value("compression", props) do
        p when p in ["zlib_json", "zlib"] -> :zlib
        _ -> nil
      end

    encoding =
      case :proplists.get_value("encoding", props) do
        "etf" -> :etf
        _ -> :json
      end

    ip = request.headers["x-forwarded-for"]

    state = %__MODULE__{
      ip: ip,
      encoding: encoding,
      compression: compression,
      callers: get_callers(request)
    }

    {:cowboy_websocket, request, state}
  end

  @auth_timeout Application.compile_env(:handler, :websocket_auth_timeout)

  @impl true
  def websocket_init(state) do
    Process.send_after(self(), :auth_timeout, @auth_timeout)
    Process.put(:"$callers", state.callers)

    {:ok, state}
  end


  #######################################################################
  ## API

  @typep command :: :cow_ws.frame() | {:shutdown, :normal}
  @typep call_result :: {[command], state}

  # exit
  def exit(pid), do: send(pid, :exit)
  @spec exit_impl(state) :: call_result
  defp exit_impl(state) do
    # note the remote webserver will then close the connection.  The
    # second command forces a shutdown in case the client is a jerk and
    # tries to DOS us by holding open connections.
    # frontend expects 4003
    ws_push([{:close, 4003, "killed by server"}, shutdown: :normal], state)
  end

  # auth timeout
  @spec auth_timeout_impl(state) :: call_result
  defp auth_timeout_impl(state) do
    if state.user do
      ws_push(nil, state)
    else
      ws_push([{:close, 1000, "authorization"}, shutdown: :normal], state)
    end
  end


  # transitional remote_send message
  def remote_send(socket, message), do: send(socket, {:remote_send, message})

  @spec remote_send_impl(json(), state) :: call_result
  defp remote_send_impl(message, state) do
    ws_push(prepare_socket_msg(message, state), state)
  end

  ##########################################################################
  ## CHAT MESSAGES

  defp real_chat_impl(
         {"chat:" <> _room_id, message},
         %__MODULE__{} = state
       ) do
    # TODO: make this guard against room_id or self_id when we put room into the state.
    message
    |> adopt_version(state)
    |> prepare_socket_msg(state)
    |> ws_push(state)
  end

  def chat_impl(
        {"chat:" <> _room_id,
         %Websocket.Message{payload: %Websocket.Message.Chat.Send{from: from, isWhisper: isWhisper}}} =
          p1,
        %__MODULE__{} = state
      ) do
    if (isWhisper == true and not is_nil(state.user) and state.user.whisperPrivacySetting == :off) or
         Enum.any?(state.user_ids_i_am_blocking, &(&1 == from)) do
      ws_push(nil, state)
    else
      real_chat_impl(p1, state)
    end
  end

  def chat_impl(
        {"chat:" <> _room_id, _} = p1,
        %__MODULE__{} = state
      ) do
    # TODO: make this guard against room_id or self_id when we put room into the state.
    real_chat_impl(p1, state)
  end

  def chat_impl(_, state), do: ws_push(nil, state)


  ##########################################################################
  ## WEBSOCKET API

  @impl true
  def websocket_handle({:text, "ping"}, state), do: {[text: "pong"], state}

  # this is for firefox
  @impl true
  def websocket_handle({:ping, _}, state), do: {[text: "pong"], state}

  def websocket_handle({:text, command_json}, state) do
    with {:ok, message_map!} <- Jason.decode(command_json),
         message_map! = Websocket.Translator.translate_inbound(message_map!),
         {:ok, message = %{errors: nil}} <- validate(message_map!, state),
         :ok <- auth_check(message, state) do
      # make the state adopt the version of the inbound message.
      new_state =
        if message.operator == Websocket.Message.Auth.Request do
          adopt_version(state, message)
        else
          state
        end

      dispatch(message, new_state)
    else
      {:error, :auth} ->
        ws_push({:close, 4004, "not_authenticated"}, state)

      {:error, %Jason.DecodeError{}} ->
        ws_push({:close, 4001, "invalid input"}, state)

      # error validating the inner changeset.
      {:ok, error} ->
        error
        |> Map.put(:operator, error.inbound_operator)
        |> prepare_socket_msg(state)
        |> ws_push(state)

      {:error, changeset = %Ecto.Changeset{}} ->
        %{errors: Websocket.Utils.Errors.changeset_errors(changeset)}
        |> prepare_socket_msg(state)
        |> ws_push(state)
    end
  end

  import Ecto.Changeset

  @spec validate(map, state) :: {:ok, Websocket.Message.t()} | {:error, Ecto.Changeset.t()}

  def validate(message, state) do
    message
    |> Websocket.Message.changeset(state)
    |> apply_action(:validate)
  end

  def auth_check(%{operator: op}, state), do: op.auth_check(state)

  def dispatch(message, state) do
    case message.operator.execute(message.payload, state) do
      close when elem(close, 0) == :close ->
        ws_push(close, state)

      {:error, err} ->
        message
        |> wrap_error(err)
        |> prepare_socket_msg(state)
        |> ws_push(state)

      {:error, errors, new_state} ->
        message
        |> wrap_error(errors)
        |> prepare_socket_msg(new_state)
        |> ws_push(new_state)

      {:noreply, new_state} ->
        ws_push(nil, new_state)

      {:reply, payload, new_state} ->
        message
        |> wrap(payload)
        |> prepare_socket_msg(new_state)
        |> ws_push(new_state)
    end
  end

  def wrap(message, payload = %{}) do
    %{message | operator: message.inbound_operator <> ":reply", payload: payload}
  end

  defp wrap_error(message, error) do
    Map.merge(
      message,
      %{
        payload: nil,
        operator: message.inbound_operator,
        errors: to_map(error)
      }
    )
  end

  # we expect three types of errors:
  # - Changeset errors
  # - textual errors
  # - anything else
  # this common `to_map` function handles them all.

  defp to_map(changeset = %Ecto.Changeset{}) do
    Websocket.Utils.Errors.changeset_errors(changeset)
  end

  defp to_map(string) when is_binary(string) do
    %{message: string}
  end

  defp to_map(other) do
    %{message: inspect(other)}
  end



  def prepare_socket_msg(data, state) do
    data
    |> encode_data(state)
    |> prepare_data(state)
  end

  defp encode_data(data, %{encoding: :etf}) do
    data
    |> Map.from_struct()
    |> :erlang.term_to_binary()
  end

  defp encode_data(data, %{encoding: :json}) do
    Jason.encode!(data)
  end

  defp prepare_data(data, %{compression: :zlib}) do
    z = :zlib.open()

    :zlib.deflateInit(z)
    data = :zlib.deflate(z, data, :finish)
    :zlib.deflateEnd(z)

    {:binary, data}
  end

  defp prepare_data(data, %{encoding: :etf}) do
    {:binary, data}
  end

  defp prepare_data(data, %{encoding: :json}) do
    {:text, data}
  end

  def ws_push(frame, state) do
    {List.wrap(frame), state}
  end

  def adopt_version(target = %{version: _}, %{version: version}) do
    %{target | version: version}
  end

  ########################################################################
  # test helper functions

  if Mix.env() == :test do
    defp get_callers(request) do
      request_bin = :cowboy_req.header("user-agent", request)

      List.wrap(
        if is_binary(request_bin) do
          request_bin
          |> Base.decode16!()
          |> :erlang.binary_to_term()
        end
      )
    end
  else
    defp get_callers(_), do: []
  end

  # ROUTER
  @impl true
  def websocket_info({:EXIT, _, _}, state), do: exit_impl(state)
  def websocket_info(:exit, state), do: exit_impl(state)
  def websocket_info(:auth_timeout, state), do: auth_timeout_impl(state)
  def websocket_info({:remote_send, message}, state), do: remote_send_impl(message, state)
  def websocket_info(message = {"chat:" <> _, _}, state), do: chat_impl(message, state)


  # throw out all other messages
  def websocket_info(_, state) do
    ws_push(nil, state)
  end
end
