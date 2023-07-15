defmodule Beef.Schemas.User do
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

  @doc false
  def changeset(user, attrs) do
    # TODO: amend this to accept *either* githubId or twitterId and also
    # pipe edit_changeset into this puppy.
    user
    |> cast(attrs, ~w(username githubId avatarUrl bannerUrl)a)
    |> validate_required([:username, :githubId, :avatarUrl, :bannerUrl])
  end

  def api_key_changeset(user, attrs) do
    user
    |> cast(attrs, [
      :apiKey
    ])
  end

  def admin_update_changeset(user, attrs) do
    user
    |> cast(attrs, [
      :staff,
      :contributions
    ])
  end

  def edit_changeset(user, attrs) do
    user
    |> cast(attrs, [
      :id,
      :username,
      :bio,
      :displayName,
      :avatarUrl,
      :bannerUrl,
      :apiKey,
      :botOwnerId
    ])
    |> validate_required([:username, :displayName, :avatarUrl])
    |> update_change(:displayName, &String.trim/1)
    |> validate_length(:bio, min: 0, max: 160)
    |> validate_length(:displayName, min: 2, max: 50)
    |> validate_format(:username, ~r/^[\w\.]{4,15}$/)
    |> validate_format(
      :avatarUrl,
      ~r/^https?:\/\/(www\.|)((a|p)bs.twimg.com\/(profile_images|sticky\/default_profile_images)\/(.*)\.(jpg|png|jpeg|webp)|avatars\.githubusercontent\.com\/u\/[^\s]+|github.com\/identicons\/[^\s]+|cdn.discordapp.com\/avatars\/[^\s]+\/[^\s]+\.(jpg|png|jpeg|webp))/
    )
    |> validate_format(
      :bannerUrl,
      ~r/^https?:\/\/(www\.|)(pbs.twimg.com\/profile_banners\/(.+)\/(.+)(?:\.(jpg|png|jpeg|webp))?|avatars\.githubusercontent\.com\/u\/)/
    )
    |> unique_constraint(:username)
  end

  defimpl Jason.Encoder do
    @fields ~w(id whisperPrivacySetting username avatarUrl bannerUrl bio online contributions staff
  lastOnline currentRoomId currentRoom displayName numFollowing numFollowers
  youAreFollowing followsYou botOwnerId roomPermissions iBlockedThem)a

    defp transform_current_room(fields = %{currentRoom: %Ecto.Association.NotLoaded{}}) do
      Map.delete(fields, :currentRoom)
    end

    defp transform_current_room(fields), do: fields

    def encode(user, opts) do
      user
      |> Map.take(@fields)
      |> transform_current_room
      |> Jason.Encoder.encode(opts)
    end
  end
end
