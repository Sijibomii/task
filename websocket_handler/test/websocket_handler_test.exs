defmodule WebsocketHandlerTest do
  use ExUnit.Case
  doctest WebsocketHandler

  test "greets the world" do
    assert WebsocketHandler.hello() == :world
  end
end
