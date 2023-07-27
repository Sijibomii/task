-- ----------------------------
-- Records of projects
-- ----------------------------

INSERT INTO projects (id, createdOn, description, label, members_count, creator_id, team_id)
VALUES
  ('uuid_project1', '2023-07-18 12:34:56', 'Project 1 description', 'Project 1 label', 3, 'uuid_user1', 'uuid_team1'),
  ('uuid_project2', '2023-07-18 12:34:56', 'Project 2 description', 'Project 2 label', 3, 'uuid_user2', 'uuid_team1'),

  ('uuid_project3', '2023-07-18 12:34:56', 'Project 3 description', 'Project 3 label', 2, 'uuid_user6', 'uuid_team2'),
  ('uuid_project4', '2023-07-18 12:34:56', 'Project 4 description', 'Project 4 label', 3, 'uuid_user8', 'uuid_team2'),

  ('uuid_project5', '2023-07-18 12:34:56', 'Project 5 description', 'Project 5 label', 3, 'uuid_user5', 'uuid_team3'),
  
  ('uuid_project6', '2023-07-18 12:34:56', 'Project 6 description', 'Project 6 label', 2, 'uuid_user10', 'uuid_team4'),
;

INSERT INTO project_memeberships (users_id, projects_id)
VALUES
  ('uuid_user1', 'uuid_project1'),
  ('uuid_user4', 'uuid_project1'),
  ('uuid_user3', 'uuid_project1'),

  ('uuid_user2', 'uuid_project1'),
  ('uuid_user4', 'uuid_project1'),
  ('uuid_user5', 'uuid_project1'),

  ('uuid_user2', 'uuid_project3'),
  ('uuid_user6', 'uuid_project3'),

  ('uuid_user7', 'uuid_project4'),
  ('uuid_user8', 'uuid_project4'),
  ('uuid_user2', 'uuid_project4'),

  ('uuid_user5', 'uuid_project5'),
  ('uuid_user6', 'uuid_project5'),
  ('uuid_user7', 'uuid_project5'),

  ('uuid_user5', 'uuid_project6'),
  ('uuid_user10', 'uuid_project6'),
;