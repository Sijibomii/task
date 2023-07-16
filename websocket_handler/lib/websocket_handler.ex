defmodule WebsocketHandler do
  use Application

  def start(_type, _args) do
    import Supervisor.Spec, warn: false

    children = [
      # top-level supervisor for UserSession group
      worker(Kaffe.Consumer, [])
      WebSocketHandler.Supervisors.UserSession,

      Plug.Cowboy.child_spec(
        scheme: :http,
        plug: Broth,
        options: [
          port: String.to_integer(System.get_env("PORT") || "6000"),
          dispatch: dispatch(),
          protocol_options: [idle_timeout: :infinity]
        ]
      )
    ]

    opts = [strategy: :one_for_one, name: WebsocketHandler.Supervisor]
    case Supervisor.start_link(children, opts) do
      {:ok, pid} ->
        start_kafka()


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


  defp start_kafka() do
    n = Application.get_env(:websocket, :num_comsumers, 1) - 1

    IO.puts("about to start kafka consumers")

    0..n
    |> Enum.map(&WebsocketHandler.Utils.KafkaIds.idx_to_str_id/1)
    |> Enum.each(fn id ->
      Kafka.Consumer.start_supervised(id)
    end)

  end
end
