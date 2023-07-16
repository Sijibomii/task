defmodule Websocket.Utils.TokenUtils do

  alias Websocket.Utils.UUID


  def verify(access_token!) do
    access_token! = access_token! || ""

    case Websocket.AccessToken.verify_and_validate(access_token!) do
      {:ok, claims} -> {:existing_claim, claims}

      _ -> nil

    end
  end


end
