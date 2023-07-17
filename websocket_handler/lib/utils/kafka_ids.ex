defmodule WebsocketHandler.Utils.KafkaIds do
  def idx_to_str_id(n) do
    case n do
      0 -> ""
      x -> Integer.to_string(x)
    end
  end

  def get_next_voice_server_id() do
    idx_to_str_id(:rand.uniform( Application.get_env(:websocket_handler, :num_comsumers, 1)) - 1)
  end
end
