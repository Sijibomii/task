-- ----------------------------
-- Records of tasks
-- ----------------------------


INSERT INTO tasks (id, created_on, description, heading, comment_count, assignee_count, status, supervised, creator_id, category_id, seed)
VALUES

  -- project_board 1 tasks
  ('7b150ce9-122c-4ea3-87c5-233190d1f746', '2023-07-18 12:34:56', 'Task 1 description', 'Task 1 heading', 3, 2, '1c5c5b2a-01fc-41f0-b98d-03f361c6b84c', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '4c7e9033-92d6-4399-ba27-11c14df6a2ac', true),
  ('53c5c5cc-d70f-4399-882d-18161e1c3d35', '2023-07-18 12:34:56', 'Task 2 description', 'Task 2 heading', 3, 2, 'd44c2e42-30c8-4917-a227-95afcd3b0ee9', true, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '1d8f31ca-380e-4179-87f2-17f6bb77ea11', true),
  ('159e1b65-33d2-45b9-9bfe-aa798a3a9d24', '2023-07-18 12:34:56', 'Task 3 description', 'Task 3 heading', 3, 2, '2a0e3c45-90b1-44da-93a3-3df0b7c70c68', true, 'c75a2b32-98b3-45e0-91db-4f1c843d45f8', '26e0ebe2-2f80-4c13-aa95-b8c123a1c0c6', true),
  ('c93b36d8-3d09-4e80-999c-4a78122a5ed9', '2023-07-18 12:34:56', 'Task 4 description', 'Task 4 heading', 3, 2, '15a4e823-2bc0-4ae2-8fc5-9efc0f03f05d', true, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '3b6a3b25-6e3e-4723-95f7-697b29d5f614', true),
  ('f95aa726-e31b-42fc-96a5-76e51b3cfcee', '2023-07-18 12:34:56', 'Task 5 description', 'Task 5 heading', 3, 2, '2a0e3c45-90b1-44da-93a3-3df0b7c70c68', true, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'c7f23a3d-9567-4be5-aa7b-4b9b05b7e52d', true),
  ('d6d53ad6-ecaf-41e5-87b2-5f56f0ee0b1c', '2023-07-18 12:34:56', 'Task 6 description', 'Task 6 heading', 3, 2, 'd44c2e42-30c8-4917-a227-95afcd3b0ee9', true, 'c75a2b32-98b3-45e0-91db-4f1c843d45f8', '9a10f46b-39a0-4e71-8aa6-89606d6bc4af', true),
  ('dfc60d60-3d78-4847-b849-9bc6f6e2d057', '2023-07-18 12:34:56', 'Task 7 description', 'Task 7 heading', 3, 2, '15a4e823-2bc0-4ae2-8fc5-9efc0f03f05d', true, 'c75a2b32-98b3-45e0-91db-4f1c843d45f8', '913f587e-3f62-40c5-97e5-9e001e031e19', true),
  ('0a40de0d-700c-49cc-9f4f-0cb835c23557', '2023-07-18 12:34:56', 'Task 8 description', 'Task 8 heading', 3, 2, '2a0e3c45-90b1-44da-93a3-3df0b7c70c68', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '2a01b4f1-0b75-4136-9e47-968bf831f058', true),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '2023-07-18 12:34:56', 'Task 9 description', 'Task 9 heading', 3, 2, '1c5c5b2a-01fc-41f0-b98d-03f361c6b84c', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '4c7e9033-92d6-4399-ba27-11c14df6a2ac', true),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '2023-07-18 12:34:56', 'Task 10 description', 'Task 10 heading', 3, 2, 'd44c2e42-30c8-4917-a227-95afcd3b0ee9', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '1d8f31ca-380e-4179-87f2-17f6bb77ea11', true),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '2023-07-18 12:34:56', 'Task 11 description', 'Task 11 heading', 3, 2, '15a4e823-2bc0-4ae2-8fc5-9efc0f03f05d', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '26e0ebe2-2f80-4c13-aa95-b8c123a1c0c6', true),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '2023-07-18 12:34:56', 'Task 12 description', 'Task 12 heading', 3, 2, '2a0e3c45-90b1-44da-93a3-3df0b7c70c68', true, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '3b6a3b25-6e3e-4723-95f7-697b29d5f614', true),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '2023-07-18 12:34:56', 'Task 13 description', 'Task 13 heading', 3, 2, '1c5c5b2a-01fc-41f0-b98d-03f361c6b84c', true, 'c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'c7f23a3d-9567-4be5-aa7b-4b9b05b7e52d', true),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', '2023-07-18 12:34:56', 'Task 14 description', 'Task 14 heading', 3, 2, 'd44c2e42-30c8-4917-a227-95afcd3b0ee9', true, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '9a10f46b-39a0-4e71-8aa6-89606d6bc4af', true),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '2023-07-18 12:34:56', 'Task 15 description', 'Task 15 heading', 3, 2, '15a4e823-2bc0-4ae2-8fc5-9efc0f03f05d', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '913f587e-3f62-40c5-97e5-9e001e031e19', true),
  ('f96a4e1a-08f8-4eaf-8f32-05492cf21725', '2023-07-18 12:34:56', 'Task 16 description', 'Task 16 heading', 3, 2, '2a0e3c45-90b1-44da-93a3-3df0b7c70c68', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '2a01b4f1-0b75-4136-9e47-968bf831f058', true),
  ('68fb1179-62d9-48f6-b6d8-683146fc739f', '2023-07-18 12:34:56', 'Task 17 description', 'Task 17 heading', 3, 2, '1c5c5b2a-01fc-41f0-b98d-03f361c6b84c', true, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '4c7e9033-92d6-4399-ba27-11c14df6a2ac', true),

   -- project_board 2 tasks
  ('49b256f1-84d5-46e9-a03c-20f1839465e1', '2023-07-18 12:34:56', 'Task 18 description', 'Task 18 heading', 1, 1, 'd44c2e42-30c8-4917-a227-95afcd3b0ee9', true, '754a17aa-8353-4fc3-b1db-6a48211cd87b', '4c7e9033-92d6-4399-ba27-11c14df6a2ac', true);


INSERT INTO task_assignees (users_id, tasks_id)
VALUES
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '159e1b65-33d2-45b9-9bfe-aa798a3a9d24'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '159e1b65-33d2-45b9-9bfe-aa798a3a9d24'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'c93b36d8-3d09-4e80-999c-4a78122a5ed9'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', 'c93b36d8-3d09-4e80-999c-4a78122a5ed9'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'f95aa726-e31b-42fc-96a5-76e51b3cfcee'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', 'f95aa726-e31b-42fc-96a5-76e51b3cfcee'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', 'd6d53ad6-ecaf-41e5-87b2-5f56f0ee0b1c'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'd6d53ad6-ecaf-41e5-87b2-5f56f0ee0b1c'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', 'dfc60d60-3d78-4847-b849-9bc6f6e2d057'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'dfc60d60-3d78-4847-b849-9bc6f6e2d057'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '0a40de0d-700c-49cc-9f4f-0cb835c23557'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '0a40de0d-700c-49cc-9f4f-0cb835c23557'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '08e32d91-d5c9-44f8-84fb-c9f4979c7531'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '08e32d91-d5c9-44f8-84fb-c9f4979c7531'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '754a17aa-8353-4fc3-b1db-6a48211cd87b'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '754a17aa-8353-4fc3-b1db-6a48211cd87b'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'c75a2b32-98b3-45e0-91db-4f1c843d45f8'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'c75a2b32-98b3-45e0-91db-4f1c843d45f8'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b'),
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', 'c468e82e-07a0-4426-9b56-7d06d515e554'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'c468e82e-07a0-4426-9b56-7d06d515e554'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'e6431235-19d2-4e8a-943a-5130e5b744a8'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'e6431235-19d2-4e8a-943a-5130e5b744a8'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'f96a4e1a-08f8-4eaf-8f32-05492cf21725'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', 'f96a4e1a-08f8-4eaf-8f32-05492cf21725'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '68fb1179-62d9-48f6-b6d8-683146fc739f'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '68fb1179-62d9-48f6-b6d8-683146fc739f');