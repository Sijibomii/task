defmodule Kafka.Consumer do

  def handle_message(payload) do

    data = Jason.decode!(payload)
    # IO.puts("Received message from topic #{topic}: #{payload}")
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


    :ok
  end

  # def handle_message({topic, payload}, state) do
  #   data = Jason.decode!(payload)
  #   IO.puts("Received message from topic #{topic}: #{payload}")
  #   # case data do
  #   #   # send to a specific user
  #   #   %{"uid" => user_id} ->
  #   #     # Onion.UserSession.send_ws(user_id, nil, Map.delete(data, "uid"))

  #   #   # send to participaant of a particular task
  #   #   %{"tid" => room_id} ->
  #   #     # Onion.RoomSession.broadcast_ws(
  #   #     #   room_id,
  #   #     #   Map.delete(data, "rid")
  #   #     # )

  #   #   # send to participant of a team


  #   #   # send to participant of a org..
  #   # end

  #   {:ok, _state}
  # end
end
