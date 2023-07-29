-- ----------------------------
-- Records of organizations
-- ----------------------------


INSERT INTO organizations (id, created_on, description, members_count, creator_id, seed)
VALUES
  ('10d68df8-7c78-4d58-b392-1c95d073a2d1', '2023-07-18 12:34:56', 'Organization 1 description', 8, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', true),
  ('00e02cc5-d52b-48e0-b49a-b8f5c97a0eab', '2023-07-18 12:34:56', 'Organization 2 description', 7, '754a17aa-8353-4fc3-b1db-6a48211cd87b', true);


INSERT INTO memeberships (users_id, organizations_id, true)
VALUES
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('f96a4e1a-08f8-4eaf-8f32-05492cf21725', '10d68df8-7c78-4d58-b392-1c95d073a2d1'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('49b256f1-84d5-46e9-a03c-20f1839465e1', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab'),
  ('68fb1179-62d9-48f6-b6d8-683146fc739f', '00e02cc5-d52b-48e0-b49a-b8f5c97a0eab');