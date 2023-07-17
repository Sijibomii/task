defmodule WebSocketHandler.Plug do
  import Plug.Conn

  @type json :: String.t() | number | boolean | nil | [json] | %{String.t() => json}

  use Plug.Router

  if Mix.env() == :test do
    plug(:set_callers)

    defp get_callers(%Plug.Conn{req_headers: req_headers}) do
      {_, request_bin} = Enum.find(req_headers, fn {key, _} -> key == "user-agent" end)

      List.wrap(
        if is_binary(request_bin) do
          request_bin
          |> Base.decode16!()
          |> :erlang.binary_to_term()
        end
      )
    end

    defp set_callers(conn, _params) do
      Process.put(:"$callers", get_callers(conn))
      conn
    end
  end


  plug(WebSocketHandler.Plugs.Cors)
  plug(:match)
  plug(:dispatch)

  options _ do
    send_resp(conn, 200, "")
  end

  forward("/index", to: WebSocketHandler.Routes.Index)

  get _ do
    send_resp(conn, 404, "not found")
  end

  post _ do
    send_resp(conn, 404, "not found")
  end
end
