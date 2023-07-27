-- ----------------------------
-- Records of organizations
-- ----------------------------

INSERT INTO organizations (id, createdOn, description, members_count, creator_id)
VALUES
  ('uuid_organization1', '2023-07-18 12:34:56', 'Organization 1 description', 8, 'uuid_user1'),
  ('uuid_organization2', '2023-07-18 12:34:56', 'Organization 2 description', 7, 'uuid_user2'),
;

INSERT INTO memeberships (users_id, organizations_id)
VALUES
  ('uuid_user1', 'uuid_organization1'),
  ('uuid_user2', 'uuid_organization1'),
  ('uuid_user3', 'uuid_organization1'),
  ('uuid_user4', 'uuid_organization1'),
  ('uuid_user5', 'uuid_organization1'),
  ('uuid_user6', 'uuid_organization1'),
  ('uuid_user7', 'uuid_organization1'),
  ('uuid_user8', 'uuid_organization1'),
  ('uuid_user2', 'uuid_organization2'),
  ('uuid_user4', 'uuid_organization2'),
  ('uuid_user5', 'uuid_organization2'),
  ('uuid_user6', 'uuid_organization2'),
  ('uuid_user7', 'uuid_organization2'),
  ('uuid_user9', 'uuid_organization2'),
  ('uuid_user10', 'uuid_organization2'),
;