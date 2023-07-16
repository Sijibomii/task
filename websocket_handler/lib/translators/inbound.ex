defmodule Websocket.Translator.Inbound do

  @operator_translations %{
    "send_room_chat_msg" => "chat:send_msg",
  }

  @operators Map.keys(@operator_translations)

  defguard translates(message) when :erlang.map_get("op", message) in @operators

  def translate_inbound(message = %{"op" => operator}) do
    message
    |> translate_operation
    |> translate_in_body(operator)
    |> add_in_ref(operator)
  end

  def translate_operation(message = %{"op" => operator}) do
    put_in(message, ["op"], @operator_translations[operator])
  end

  # incase I need to make additional changes to the websocket message body
  def translate_in_body(message, "edit_profile") do
    put_in(message, ["d"], get_in(message, ["d", "data"]))
  end



  def translate_in_body(message, _op), do: message

  @casts_to_calls ~w(auth)

  def add_in_ref(message, op) when op in @casts_to_calls do
    Map.put(message, "fetchId", UUID.uuid4())
  end


  def add_in_ref(message, _op), do: message

end
