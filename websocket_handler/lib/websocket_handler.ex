defmodule WebsocketHandler do
  use Application

  def start(_type, _args) do


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


  defp start_kafka() do
    n = Application.get_env(:websocket, :num_comsumers, 1) - 1

    IO.puts("about to start kafka consumers")

    0..n
    |> Enum.map(&Kousa.Utils.VoiceServerUtils.idx_to_str_id/1)
    |> Enum.each(fn id ->
      Onion.VoiceRabbit.start_supervised(id)
      Onion.VoiceOnlineRabbit.start_supervised(id)
    end)

    IO.puts("finished rabbits")
  end
end
