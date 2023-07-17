defmodule WebsocketHandler do
  use Application

  def start(_type, _args) do
    import Supervisor.Spec, warn: false

    children = [
      # top-level supervisor for UserSession group

      WebSocketHandler.Supervisors.UserSession,

      Plug.Cowboy.child_spec(
        scheme: :http,
        options: [
          plug: Broth,
          port: String.to_integer(System.get_env("PORT") || "6000"),
          dispatch: dispatch(),
          protocol_options: [idle_timeout: :infinity]
        ]
      )
    ]

    opts = [strategy: :one_for_one, name: WebsocketHandler.Supervisor]
    case Supervisor.start_link(children, opts) do
      {:ok, pid} ->
        IO.puts("app running on pid : #{pid}")
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
         {:_, Plug.Cowboy.Handler, {Websocket, []}}
       ]}
    ]
  end

end
