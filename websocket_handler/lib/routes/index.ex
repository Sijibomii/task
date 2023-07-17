defmodule WebSocketHandler.Routes.Index do
  import Plug.Conn

  use Plug.Router

  plug(WebSocketHandler.Plugs.Cors)
  plug(:match)
  plug(:dispatch)

  get "/" do
    conn
    |> put_resp_content_type("application/json")
    |> send_resp(
      200,
      Jason.encode!(%{message: "websocket handler for task app"})
    )
  end

end
