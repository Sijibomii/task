-- ----------------------------
-- Records of channel
-- ----------------------------


INSERT INTO channels (id, creator_id, createdOn, channelType, organization_id, team_id, project_id)
VALUES
  ('uuid_channel1', 'uuid_user1', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team1', NULL),
  ('uuid_channel2', 'uuid_user3', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team1', NULL), 
  ('uuid_channel3', 'uuid_user2', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team2', NULL),
  ('uuid_channel4', 'uuid_user6', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team2', NULL),
  ('uuid_channel5', 'uuid_user4', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team3', NULL),
  ('uuid_channel6', 'uuid_user5', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team3', NULL),
  ('uuid_channel7', 'uuid_user5', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team4', NULL),
  ('uuid_channel8', 'uuid_user10', '2023-07-18 12:34:56', 'TEAM', NULL, 'uuid_team4', NULL),

  ('uuid_channel9', 'uuid_user4', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project1'),
  ('uuid_channel10', 'uuid_user5', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project2'),
  ('uuid_channel11', 'uuid_user6', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project3'),
  ('uuid_channel12', 'uuid_user8', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project4'),
  ('uuid_channel13', 'uuid_user7', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project5'),
  ('uuid_channel14', 'uuid_user10', '2023-07-18 12:34:56', 'PROJECT', NULL, NULL, 'uuid_project6'),


  ('uuid_channel15', 'uuid_user1', '2023-07-18 12:34:56', 'ORGANIZATION', 'uuid_organization1', NULL, NULL),
  ('uuid_channel16', 'uuid_user6', '2023-07-18 12:34:56', 'ORGANIZATION', 'uuid_organization2', NULL, NULL),
;


INSERT INTO channel_members (channel_id, user_id)
VALUES
  ('uuid_channel1', 'uuid_user1'),
  ('uuid_channel1', 'uuid_user2'),
  ('uuid_channel1', 'uuid_user3'),
  ('uuid_channel2', 'uuid_user3'),
  ('uuid_channel2', 'uuid_user4'),
  ('uuid_channel2', 'uuid_user5'),
  ('uuid_channel3', 'uuid_user2'),
  ('uuid_channel3', 'uuid_user6'),
  ('uuid_channel3', 'uuid_user7'),
  ('uuid_channel4', 'uuid_user6'),
  ('uuid_channel4', 'uuid_user8'),
  ('uuid_channel4', 'uuid_user7'),
  ('uuid_channel5', 'uuid_user4'),
  ('uuid_channel5', 'uuid_user2'),
  ('uuid_channel5', 'uuid_user4'),
  ('uuid_channel6', 'uuid_user5'),
  ('uuid_channel6', 'uuid_user9'),
  ('uuid_channel6', 'uuid_user7'),
  ('uuid_channel7', 'uuid_user5'),
  ('uuid_channel7', 'uuid_user4'),
  ('uuid_channel7', 'uuid_user10'),
  ('uuid_channel8', 'uuid_user10'),
  ('uuid_channel8', 'uuid_user5'),
  ('uuid_channel8', 'uuid_user4'),

  ('uuid_channel9', 'uuid_user4'),
  ('uuid_channel9', 'uuid_user1'),
  ('uuid_channel9', 'uuid_user3'),
  ('uuid_channel10', 'uuid_user5'),
  ('uuid_channel10', 'uuid_user4'),
  ('uuid_channel10', 'uuid_user2'),
  ('uuid_channel11', 'uuid_user6'),
  ('uuid_channel11', 'uuid_user2'),
  ('uuid_channel13', 'uuid_user7'),
  ('uuid_channel13', 'uuid_user5'),
  ('uuid_channel13', 'uuid_user6'),

  ('uuid_channel15', 'uuid_user1'),
  ('uuid_channel15', 'uuid_user2'),
  ('uuid_channel15', 'uuid_user3'),
  ('uuid_channel16', 'uuid_user5'),
  ('uuid_channel16', 'uuid_user6'),
  ('uuid_channel16', 'uuid_user7'),
  ('uuid_channel16', 'uuid_user9'),
  ('uuid_channel16', 'uuid_user10'),
  -- Add more rows as needed
;
