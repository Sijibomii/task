defmodule Websocket.Auth do

  alias Websocket.Utils.TokenUtils
  alias Models.Schemas.User
  alias Kafka.Producer
  import Jason

  @spec authenticate(Websocket.Message.Auth.Request.t()) ::
          {:ok, Models.Schemas.User.t()} | {:error, term}
  def authenticate(request) do
    case TokenUtils.verify(request.accessToken) do
      nil ->
        {:error, "invalid_authentication"}

      {:existing_claim, user_claims} ->
        do_auth(user_claims["user_id"], user_claims["email"], user_claims["username"], user_claims["display_name"])

    end
  end

  defp do_auth(user_id, user_email, username, display_name) do
    alias WebSocketHandler.UserSession

    if user do
      # note that this will start the session and will be ignored if the
      # session is already running.
      UserSession.start_supervised(
        user_id: user_id,
        username: usernam
        email: user_email,
        display_name: display_name,
      )

      UserSession.set_active_ws(user.id, self())

      # get all groups and org user belongs to send them a message to include this user pid when a broadcast is needed
      # send kafka message to get groups and org by user
      Producer.send_message("user", Jason.encode!(%{
        "operator" => "get:user:details",
        "fetchId" => UUID.uuid4(),
        "data" => %{
          "user_id" => user_id,
        }
      }))

      # subscribe to chats directed to oneself.
      PubSub.subscribe("chat:" <> user.id)

      {:ok, %User{
        id: user_id,
        email: user_email,
        username: username,
        display_name: display_name
      }}
    else
      {:close, 4001, "invalid_authentication"}
    end
  end
end
