-- ----------------------------
-- Records of tasks
-- ----------------------------

INSERT INTO tasks (id, createdOn, description, heading, comment_count, assignee_count, status, supervised, creator_id, category_id)
VALUES

  -- project_board 1 tasks
  ('uuid_task1', '2023-07-18 12:34:56', 'Task 1 description', 'Task 1 heading', 3, 2, 'uuid_status1', true, 'uuid_user1', 'uuid_category1'),
  ('uuid_task2', '2023-07-18 12:34:56', 'Task 2 description', 'Task 2 heading', 3, 2, 'uuid_status2', true, 'uuid_user4', 'uuid_category2'),
  ('uuid_task3', '2023-07-18 12:34:56', 'Task 3 description', 'Task 3 heading', 3, 2, 'uuid_status4', true, 'uuid_user3', 'uuid_category3'),
  ('uuid_task4', '2023-07-18 12:34:56', 'Task 4 description', 'Task 4 heading', 3, 2, 'uuid_status3', true, 'uuid_user4', 'uuid_category4'),
  ('uuid_task5', '2023-07-18 12:34:56', 'Task 5 description', 'Task 5 heading', 3, 2, 'uuid_status4', true, 'uuid_user4', 'uuid_category5'),
  ('uuid_task6', '2023-07-18 12:34:56', 'Task 6 description', 'Task 6 heading', 3, 2, 'uuid_status2', true, 'uuid_user3', 'uuid_category6'),
  ('uuid_task7', '2023-07-18 12:34:56', 'Task 7 description', 'Task 7 heading', 3, 2, 'uuid_status3', true, 'uuid_user3', 'uuid_category7'),
  ('uuid_task8', '2023-07-18 12:34:56', 'Task 8 description', 'Task 8 heading', 3, 2, 'uuid_status4', true, 'uuid_user1', 'uuid_category8'),
  ('uuid_task9', '2023-07-18 12:34:56', 'Task 9 description', 'Task 9 heading', 3, 2, 'uuid_status1', true, 'uuid_user1', 'uuid_category1'),
  ('uuid_task10', '2023-07-18 12:34:56', 'Task 10 description', 'Task 10 heading', 3, 2, 'uuid_status2', true, 'uuid_user1', 'uuid_category2'),
  ('uuid_task11', '2023-07-18 12:34:56', 'Task 11 description', 'Task 11 heading', 3, 2, 'uuid_status3', true, 'uuid_user1', 'uuid_category3'),
  ('uuid_task12', '2023-07-18 12:34:56', 'Task 12 description', 'Task 12 heading', 3, 2, 'uuid_status4', true, 'uuid_user4', 'uuid_category4'),
  ('uuid_task13', '2023-07-18 12:34:56', 'Task 13 description', 'Task 13 heading', 3, 2, 'uuid_status1', true, 'uuid_user3', 'uuid_category5'),
  ('uuid_task14', '2023-07-18 12:34:56', 'Task 14 description', 'Task 14 heading', 3, 2, 'uuid_status2', true, 'uuid_user4', 'uuid_category6'),
  ('uuid_task15', '2023-07-18 12:34:56', 'Task 15 description', 'Task 15 heading', 3, 2, 'uuid_status3', true, 'uuid_user1', 'uuid_category7'),
  ('uuid_task16', '2023-07-18 12:34:56', 'Task 16 description', 'Task 16 heading', 3, 2, 'uuid_status4', true, 'uuid_user1', 'uuid_category8'),
  ('uuid_task17', '2023-07-18 12:34:56', 'Task 17 description', 'Task 17 heading', 3, 2, 'uuid_status1', true, 'uuid_user1', 'uuid_category1'),

   -- project_board 2 tasks
  ('uuid_task18', '2023-07-18 12:34:56', 'Task 18 description', 'Task 18 heading', 1, 1, 'uuid_status2', true, 'uuid_user2'),
  -- Add more rows as needed
;


INSERT INTO task_assignees (users_id, tasks_id)
VALUES
  ('uuid_user4', 'uuid_task1'),
  ('uuid_user3', 'uuid_task1'),
  ('uuid_user1', 'uuid_task2'),
  ('uuid_user3', 'uuid_task2'),
  ('uuid_user1', 'uuid_task3'),
  ('uuid_user4', 'uuid_task3'),
  ('uuid_user3', 'uuid_task4'),
  ('uuid_user1', 'uuid_task4'),
  ('uuid_user3', 'uuid_task5'),
  ('uuid_user1', 'uuid_task5'),
  ('uuid_user1', 'uuid_task6'),
  ('uuid_user4', 'uuid_task6'),
  ('uuid_user1', 'uuid_task7'),
  ('uuid_user4', 'uuid_task7'),
  ('uuid_user4', 'uuid_task8'),
  ('uuid_user3', 'uuid_task8'),
  ('uuid_user4', 'uuid_task9'),
  ('uuid_user3', 'uuid_task9'),
  ('uuid_user4', 'uuid_task10'),
  ('uuid_user3', 'uuid_task10'),
  ('uuid_user4', 'uuid_task11'),
  ('uuid_user3', 'uuid_task11'),
  ('uuid_user1', 'uuid_task12'),
  ('uuid_user3', 'uuid_task12'),
  ('uuid_user4', 'uuid_task13'),
  ('uuid_user1', 'uuid_task13'),
  ('uuid_user1', 'uuid_task14'),
  ('uuid_user3', 'uuid_task14'),
  ('uuid_user4', 'uuid_task15'),
  ('uuid_user3', 'uuid_task15'),
  ('uuid_user4', 'uuid_task16'),
  ('uuid_user3', 'uuid_task16'),
  ('uuid_user4', 'uuid_task17'),
  ('uuid_user3', 'uuid_task17'),
;