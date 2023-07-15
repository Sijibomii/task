defmodule Kafka.Producer do
  use KafkaEx.Producer

  def send_message(topic, message) do
    KafkaEx.produce(topic, message)
  end
end
