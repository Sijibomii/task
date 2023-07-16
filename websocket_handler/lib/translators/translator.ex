defmodule Websocket.Translator do

  alias Websocket.Translator.Inbound
  require Inbound

  def translate_inbound(message) when Inbound.translates(message) do
    Inbound.translate_inbound(message)
  end

  # throw remaining out
  def translate_inbound(message), do: message

  # def translate_outbound(message, original = %{version: ~v(0.1.0)}) do
  #   V0_1_0.translate_outbound(message, original)
  # end

  # def translate_outbound(message, _), do: message

end
