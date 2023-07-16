defmodule WebSocketHandler.UserSession do
  use GenServer, restart: :temporary

  defmodule State do
    @type t :: %__MODULE__{
            user_id: String.t(),
            username: String.t(),
            display_name: String.t(),
            pid: pid()
          }

    defstruct user_id: nil,
              pid: nil,
              username: nil,
              display_name: nil
  end

  # REGISTRY AND SUPERVISION BOILERPLATE
  defp via(user_id), do: {:via, Registry, {WebSocketHandler.UserSessionRegistry, user_id}}

  defp cast(user_id, params), do: GenServer.cast(via(user_id), params)
  defp call(user_id, params), do: GenServer.call(via(user_id), params)

  def start_supervised(initial_values) do
    callers = [self() | Process.get(:"$callers", [])]

    case DynamicSupervisor.start_child(
           WebSocketHandler.UserSessionDynamicSupervisor,
           {__MODULE__, Keyword.merge(initial_values, callers: callers)}
         ) do
      {:error, {:already_started, pid}} -> {:ignored, pid}
      error -> error
    end
  end

  def child_spec(init), do: %{super(init) | id: Keyword.get(init, :user_id)}

  def count, do: Registry.count(WebSocketHandler.UserSessionRegistry)

  def lookup(user_id), do: Registry.lookup(WebSocketHandler.UserSessionRegistry, user_id)

   ###############################################################################
  ## INITIALIZATION BOILERPLATE

  def start_link(init) do
    GenServer.start_link(__MODULE__, init, name: via(init[:user_id]))
  end

  def init(init) do
    # transfer callers into the running process.
    Process.put(:"$callers", Keyword.get(init, :callers))
    {:ok, struct(State, init)}
  end


  ##############################################################################
  ## API HOOKS
  ## TODO: CHANGE CASTS TO CALLS


  ##############################################################################
  ## MESSAGING API.
  ## TODO: change the first one to a call

  defp handle_disconnect(pid, state = %{pid: pid}) do

    {:stop, :normal, state}
  end

  defp handle_disconnect(_, state), do: {:noreply, state}

  #############################################################################
  ## ROUTER



  def handle_info({:DOWN, _ref, :process, pid, _reason}, state), do: handle_disconnect(pid, state)

end
