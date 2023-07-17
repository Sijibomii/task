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
        # start broadway
        start_broadway()
        {:ok, pid}

      error ->
        error
    end
  end

  defp start_broadway() do

    IO.puts("about to broadway")

    Websocket.Broadway.start_link("")

    IO.puts("finished broadway startup")
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
