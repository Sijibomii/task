defmodule WebsocketHandler.MixProject do
  use Mix.Project

  def project do
    [
      app: :websocket_handler,
      version: "0.1.0",
      elixir: "~> 1.15",
      start_permanent: Mix.env() == :prod,
      deps: deps()
    ]
  end

  # Run "mix help compile.app" to learn about applications.
  def application do
    [
      extra_applications: [:logger],
      mod: []
    ]
  end

  # Run "mix help deps" to learn about dependencies.
  defp deps do
    [
      {:plug_cowboy, "~> 2.5"},
      {:websockex, "~> 0.4.3", only: :test},
      {:broadway_kafka, "~> 0.3"},
      {:ecto_sql, "~> 3.0"},
      {:ecto_enum, "~> 1.4"},
      {:jason, "~> 1.2"},
      {:joken, "~> 2.0"},
      {:elixir_uuid, "~> 1.2"},
    ]
  end
end
