use Mix.Config


config :kafka_ex,
  brokers: [
    {"localhost", 9092}  # Replace with your Kafka broker details
  ],
  client_id: "websocket",
  consumer_group_id: "my_consumer_group"

config :handler, websocket_auth_timeout: 10_000
config :websocket, access_token_secret: "dnqwkdnsododkpdkpdkoddnnfdnno"
