defmodule Kafka.Consumer do
  use GenServer
  use KafkaEx.Consumer, [
    topics: ["my_topic"],  # Replace with the desired Kafka topic(s) to consume from
    group_id: "my_consumer_group"  # Must match the consumer_group_id configuration
  ]

  defmodule State do
    @type t :: %{id: String.t() }

    defstruct id: ""
  end

  def start_supervised(kafka_id) do
    DynamicSupervisor.start_child(
      Kafka.ComsumerSupervisor,
      {__MODULE__, kafka_id}
    )
  end

  def start_link(kafka_id) do
    GenServer.start_link(
      __MODULE__,
      kafka_id,
      name: via(kafka_id)
    )
  end

  def handle_message({topic, payload}, state) do
    data = Jason.decode!(payload)
    IO.puts("Received message from topic #{topic}: #{payload}")
    case data do
      # send to a specific user
      %{"uid" => user_id} ->
        # Onion.UserSession.send_ws(user_id, nil, Map.delete(data, "uid"))

      # send to participaant of a particular task
      %{"tid" => room_id} ->
        # Onion.RoomSession.broadcast_ws(
        #   room_id,
        #   Map.delete(data, "rid")
        # )

      # send to participant of a team


      # send to participant of a org..
    end

    {:ok, _state}
  end
end
