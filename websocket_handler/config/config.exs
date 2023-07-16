use Mix.Config


config :kaffe,
  producer: [
    endpoints: [localhost: 9092],
    # endpoints references [hostname: port]. Kafka is configured to run on port 9092.
    # In this example, the hostname is localhost because we've started the Kafka server
    # straight from our machine. However, if the server is dockerized, the hostname will
    # be called whatever is specified by that container (usually "kafka")
    topics: ["webhandler_events"], # add a list of topics you plan to produce messages to
    consumer_group: "example-consumer-group",   # the consumer group for tracking offsets in Kafka
    message_handler: Kafka.Consumer,           # the module that will process messages
  ]

config :handler, websocket_auth_timeout: 10_000
config :websocket, access_token_secret: "dnqwkdnsododkpdkpdkoddnnfdnno"
