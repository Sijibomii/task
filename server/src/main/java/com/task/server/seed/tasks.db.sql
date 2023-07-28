-- ----------------------------
-- Records of tasks
-- ----------------------------

INSERT INTO tasks (id, createdOn, description, heading, comment_count, assignee_count, status, supervised, creator_id)
VALUES
  ('uuid_task1', '2023-07-18 12:34:56', 'Task 1 description', 'Task 1 heading', 3, 2, 'IN_PROGRESS', true, 'uuid_user1'),
  ('uuid_task2', '2023-07-18 12:34:56', 'Task 2 description', 'Task 2 heading', 1, 1, 'COMPLETED', false, 'uuid_user2'),
  -- Add more rows as needed
;
