defmodule Broth.Routes.Room do
  import Plug.Conn

  alias Beef.Rooms
  alias Ecto.UUID

  use Plug.Router

  plug(Broth.Plugs.Cors)
  plug(:match)
  plug(:dispatch)

  get "/" do

    Jason.encode!(%{message: "websocket handler for task app"})

  end
end
