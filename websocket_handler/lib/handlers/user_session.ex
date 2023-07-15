defmodule WebSocketHandler.UserSession do
  use GenServer, restart: :temporary

  defmodule State do
    @type t :: %__MODULE__{
            user_id: String.t(),
            username: String.t(),
            display_name: String.t(),
            ip: String.t(),
            pid: pid()
          }

    defstruct user_id: nil,
              pid: nil,
              username: nil,
              ip: nil
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


end
