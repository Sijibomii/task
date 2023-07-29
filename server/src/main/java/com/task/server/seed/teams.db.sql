-- ----------------------------
-- Records of teams
-- ----------------------------


INSERT INTO teams (id, created_on, description, members_count, creator_id, org_id, seed)
VALUES
  ('53c5c5cc-d70f-4399-882d-18161e1c3d35', '2023-07-18 12:34:56', 'Team 1 description', 5, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '10d68df8-7c78-4d58-b392-1c95d073a2d1', true),
  ('7b150ce9-122c-4ea3-87c5-233190d1f746', '2023-07-18 12:34:56', 'Team 2 description', 4, '754a17aa-8353-4fc3-b1db-6a48211cd87b', '10d68df8-7c78-4d58-b392-1c95d073a2d1', true),
  ('f3e5806b-17c5-4440-96c5-5720a912e48d', '2023-07-18 12:34:56', 'Team 3 description', 6, '754a17aa-8353-4fc3-b1db-6a48211cd87b', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab', true),
  ('7c79d37b-25cb-48e6-b2ac-8f4d7da9cc65', '2023-07-18 12:34:56', 'Team 4 description', 3, '60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab', true);



INSERT INTO team_memeberships (users_id, teams_id)
VALUES
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '53c5c5cc-d70f-4399-882d-18161e1c3d35'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('f96a4e1a-08f8-4eaf-8f32-05492cf21725', '7b150ce9-122c-4ea3-87c5-233190d1f746'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('49b256f1-84d5-46e9-a03c-20f1839465e1', 'f3e5806b-17c5-4440-96c5-5720a912e48d'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '7c79d37b-25cb-48e6-b2ac-8f4d7da9cc65'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '7c79d37b-25cb-48e6-b2ac-8f4d7da9cc65'),
  ('68fb1179-62d9-48f6-b6d8-683146fc739f', '7c79d37b-25cb-48e6-b2ac-8f4d7da9cc65');