defmodule Models.Schemas.User do
  use Ecto.Schema

  import Ecto.Changeset


  @timestamps_opts [type: :utc_datetime_usec]

  @type t :: %__MODULE__{
          id: Ecto.UUID.t(),
          username: String.t(),
          email: String.t(),
          display_name: String.t(),
          # orgs, groups, team...
        }

  @primary_key {:id, :binary_id, []}
  schema "users" do
    field(:username, :string)
    field(:email, :string)
    field(:display_name, :string)
    # orgs, groups, teams id
  end

  def changeset(user, attrs) do

    user
    |> cast(attrs, ~w(username email display_name id)a)
    |> validate_required([:username, :email])
  end

end
