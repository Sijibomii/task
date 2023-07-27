-- ----------------------------
-- Records of teams
-- ----------------------------


INSERT INTO teams (id, createdOn, description, members_count, creator_id, org_id)
VALUES
  ('uuid_team1', '2023-07-18 12:34:56', 'Team 1 description', 5, 'uuid_user1', 'uuid_organization1'),
  ('uuid_team2', '2023-07-18 12:34:56', 'Team 2 description', 4, 'uuid_user2', 'uuid_organization1'),
  ('uuid_team3', '2023-07-18 12:34:56', 'Team 3 description', 6, 'uuid_user2', 'uuid_organization2'),
  ('uuid_team2', '2023-07-18 12:34:56', 'Team 2 description', 3, 'uuid_user4', 'uuid_organization2'),
  -- Add more rows as needed
;


INSERT INTO team_memeberships (users_id, teams_id)
VALUES
  ('uuid_user1', 'uuid_team1'),
  ('uuid_user2', 'uuid_team1'),
  ('uuid_user3', 'uuid_team1'),
  ('uuid_user4', 'uuid_team1'),
  ('uuid_user5', 'uuid_team1'),
  ('uuid_user2', 'uuid_team2'),
  ('uuid_user6', 'uuid_team2'),
  ('uuid_user7', 'uuid_team2'),
  ('uuid_user8', 'uuid_team2'),
  ('uuid_user2', 'uuid_team3'),
  ('uuid_user4', 'uuid_team3'),
  ('uuid_user5', 'uuid_team3'),
  ('uuid_user6', 'uuid_team3'),
  ('uuid_user7', 'uuid_team3'),
  ('uuid_user9', 'uuid_team3'),
  ('uuid_user10', 'uuid_team3'),
  ('uuid_user5', 'uuid_team3'),
  ('uuid_user6', 'uuid_team3'),
;