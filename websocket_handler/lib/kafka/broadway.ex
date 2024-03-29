defmodule Websocket.Broadway do
  use Broadway

  alias Broadway.Message

  def start_link(_opts) do
    Broadway.start_link(__MODULE__,
      name: __MODULE__,
      producer: [
        module:
          {BroadwayKafka.Producer,
           [
             hosts: [localhost: 29092],
             group_id: "default-group",
             topics: ["websocket"],
            #  offset_reset_policy: :earliest,
            #  receive_interval: 0,
            #  group_config: [
            #   session_timeout_seconds: 15,
            #   rebalance_timeout_seconds: 300,

            # ],
            # fetch_config: [

            # ],
            # client_config: [

            # ],
           ]},
        concurrency: 1
      ],
      processors: [
        default: [
          concurrency: 10
        ]
      ]
    )
  end

  @impl true
  def handle_message(_, message, _) do
    data = Jason.decode!(message)
    IO.puts("Received message: #{message}")

     # case data do
    #   # send to a specific user
    #   %{"uid" => user_id} ->
    #     # Onion.UserSession.send_ws(user_id, nil, Map.delete(data, "uid"))

    #   # send to participaant of a particular task
    #   %{"tid" => room_id} ->
    #     # Onion.RoomSession.broadcast_ws(
    #     #   room_id,
    #     #   Map.delete(data, "rid")
    #     # )

    #   # send to participant of a team


    #   # send to participant of a org..
    # end

  end
end
