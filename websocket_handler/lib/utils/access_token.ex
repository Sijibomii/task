defmodule Websocket.AccessToken do

  def __default_signer__,
    do: Joken.Signer.create("HS256", Application.fetch_env!(:websocket_handler, :access_token_secret))

  use Joken.Config

  # 1 hour
  def token_config, do: default_claims(default_exp: 60 * 60)
end
