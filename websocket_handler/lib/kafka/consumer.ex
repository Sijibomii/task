defmodule Kafka.Consumer do
  use KafkaEx.Consumer, [
    topics: ["my_topic"],  # Replace with the desired Kafka topic(s) to consume from
    group_id: "my_consumer_group"  # Must match the consumer_group_id configuration
  ]

  def handle_message({topic, message}, _state) do
    # Handle the received message
    IO.puts("Received message from topic #{topic}: #{message}")
    {:ok, _state}
  end
end
