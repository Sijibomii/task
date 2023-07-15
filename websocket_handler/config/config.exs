use Mix.Config


config :kafka_ex,
  brokers: [
    {"localhost", 9092}  # Replace with your Kafka broker details
  ],
  client_id: "my_app",
  consumer_group_id: "my_consumer_group"
