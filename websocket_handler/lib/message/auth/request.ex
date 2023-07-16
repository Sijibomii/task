defmodule Websocket.Message.Auth.Request do
  use Websocket.Message.Call,
    needs_auth: false

  # passes token gotten from main server. same secret should be shared btw the two servers so this can decode
  @primary_key false
  embedded_schema do
    field(:accessToken, :string)
    field(:user_id, :string)
  end


  @impl true
  def changeset(initializer \\ %__MODULE__{}, data) do
    initializer
    |> cast(data, [:accessToken, :user_id])
    |> validate_required([:accessToken])
  end

  defmodule Reply do
    use Websocket.Message.Push

    @derive {Jason.Encoder, only: ~w(
      id
      email
    )a}

    @primary_key {:id, :binary_id, []}
    schema "users" do
      field(:email, :string)
    end
  end

  @impl true
  def execute(changeset, state) do
    with {:ok, request} <- apply_action(changeset, :validate),
         {:ok, user} <- Websocket.Auth.validate(request) do

      {:reply, user, %{state | user: user}}
    else
      # don't tolerate malformed requests with any response besides closing
      # out websocket.
      _ -> {:close, 4001, "invalid_authentication"}
    end
  end
end
