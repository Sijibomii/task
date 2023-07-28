-- ----------------------------
-- Records of tasks comments
-- ----------------------------

INSERT INTO task_comment (id, createdOn, comment, supervised, creator_id)
VALUES
  ('uuid_comment1', '2023-07-18 12:34:56', 'Task Comment 1', true, 'uuid_user1'),
  ('uuid_comment2', '2023-07-18 12:34:56', 'Task Comment 2', false, 'uuid_user4'),
  ('uuid_comment3', '2023-07-18 12:34:56', 'Task Comment 3', false, 'uuid_user3'),
  ('uuid_comment4', '2023-07-18 12:34:56', 'Task Comment 4', false, 'uuid_user1'),
  ('uuid_comment5', '2023-07-18 12:34:56', 'Task Comment 5', false, 'uuid_user1'),
  ('uuid_comment6', '2023-07-18 12:34:56', 'Task Comment 6', false, 'uuid_user1'),
  ('uuid_comment7', '2023-07-18 12:34:56', 'Task Comment 7', false, 'uuid_user1'),
  ('uuid_comment8', '2023-07-18 12:34:56', 'Task Comment 8', false, 'uuid_user4'),
  ('uuid_comment9', '2023-07-18 12:34:56', 'Task Comment 9', false, 'uuid_user4'),
  ('uuid_comment10', '2023-07-18 12:34:56', 'Task Comment 10', false, 'uuid_user4'),
  ('uuid_comment11', '2023-07-18 12:34:56', 'Task Comment 11', false, 'uuid_user3'),
  ('uuid_comment12', '2023-07-18 12:34:56', 'Task Comment 12', false, 'uuid_user3'),
  ('uuid_comment13', '2023-07-18 12:34:56', 'Task Comment 13', false, 'uuid_user3'),
  ('uuid_comment14', '2023-07-18 12:34:56', 'Task Comment 14', false, 'uuid_user4'),
  ('uuid_comment15', '2023-07-18 12:34:56', 'Task Comment 15', false, 'uuid_user3'),
  ('uuid_comment16', '2023-07-18 12:34:56', 'Task Comment 16', false, 'uuid_user4'),
  ('uuid_comment17', '2023-07-18 12:34:56', 'Task Comment 17', false, 'uuid_user1'),
  ('uuid_comment18', '2023-07-18 12:34:56', 'Task Comment 18', false, 'uuid_user4'),
  ('uuid_comment19', '2023-07-18 12:34:56', 'Task Comment 19', false, 'uuid_user4'),
  ('uuid_comment20', '2023-07-18 12:34:56', 'Task Comment 20', false, 'uuid_user3'),
  ('uuid_comment21', '2023-07-18 12:34:56', 'Task Comment 21', false, 'uuid_user4'),
  ('uuid_comment22', '2023-07-18 12:34:56', 'Task Comment 22', false, 'uuid_user3'),
  ('uuid_comment23', '2023-07-18 12:34:56', 'Task Comment 23', false, 'uuid_user4'),
  ('uuid_comment24', '2023-07-18 12:34:56', 'Task Comment 24', false, 'uuid_user3'),
  ('uuid_comment25', '2023-07-18 12:34:56', 'Task Comment 25', false, 'uuid_user4'),
  ('uuid_comment26', '2023-07-18 12:34:56', 'Task Comment 26', false, 'uuid_user4'),
  ('uuid_comment27', '2023-07-18 12:34:56', 'Task Comment 27', false, 'uuid_user3'),
  ('uuid_comment28', '2023-07-18 12:34:56', 'Task Comment 28', false, 'uuid_user4'),
  ('uuid_comment29', '2023-07-18 12:34:56', 'Task Comment 29', false, 'uuid_user3'),
  ('uuid_comment30', '2023-07-18 12:34:56', 'Task Comment 30', false, 'uuid_user4'),
  ('uuid_comment31', '2023-07-18 12:34:56', 'Task Comment 31', false, 'uuid_user3'),
  ('uuid_comment32', '2023-07-18 12:34:56', 'Task Comment 32', false, 'uuid_user4'),
  ('uuid_comment33', '2023-07-18 12:34:56', 'Task Comment 33', false, 'uuid_user3'),
  ('uuid_comment34', '2023-07-18 12:34:56', 'Task Comment 34', false, 'uuid_user4'),
;

INSERT INTO comment_tags (taskcomment_id, users_id)
VALUES
  ('uuid_comment1', 'uuid_user1'),
  ('uuid_comment2', 'uuid_user4'),
  ('uuid_comment3', 'uuid_user3'),
;