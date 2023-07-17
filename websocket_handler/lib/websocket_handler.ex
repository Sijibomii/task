defmodule WebsocketHandler do
  use Application

  def start(_type, _args) do
    import Supervisor.Spec, warn: false

    children = [
      # top-level supervisor for UserSession group

      WebSocketHandler.Supervisors.UserSession,
      Plug.Cowboy.child_spec(
        scheme: :http,
        plug: WebSocketHandler.Plug,
        options: [
          port: String.to_integer(System.get_env("PORT") || "4001"),
          dispatch: dispatch(),
          protocol_options: [idle_timeout: :infinity]
        ]
      )
    ]

    opts = [strategy: :one_for_one, name: WebsocketHandler.Supervisor]
    case Supervisor.start_link(children, opts) do
      {:ok, pid} ->
        IO.inspect(pid, label: "PID")
        {:ok, pid}

      error ->
        error
    end
  end

  # socket
  defp dispatch do
    [
      {:_,
       [
         {"/socket", Websocket.SocketHandler, []},
         {:_, Plug.Cowboy.Handler, {WebSocketHandler.Plug, []}}
       ]}
    ]
  end

end
