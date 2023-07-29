-- ----------------------------
-- Records of projects
-- ----------------------------


INSERT INTO projects (id, created_on, description, label, members_count, creator_id, team_id, seed)
VALUES
  ('3d37f6c1-4b7b-404e-8e5d-649d90f8498c', '2023-07-18 12:34:56', 'Project 1 description', 'Project 1 label', 3, '08e32d91-d5c9-44f8-84fb-c9f4979c7531', '53c5c5cc-d70f-4399-882d-18161e1c3d35', true),
  ('d1488f27-25e1-4d03-af29-ee652dd658bb', '2023-07-18 12:34:56', 'Project 2 description', 'Project 2 label', 3, '754a17aa-8353-4fc3-b1db-6a48211cd87b', '53c5c5cc-d70f-4399-882d-18161e1c3d35', true),

  ('f202b048-aa24-4b6e-979e-69f2922544a5', '2023-07-18 12:34:56', 'Project 3 description', 'Project 3 label', 2, 'c468e82e-07a0-4426-9b56-7d06d515e554', '7b150ce9-122c-4ea3-87c5-233190d1f746', true),
  ('8a189662-eb77-4b57-a930-60233f65bb5e', '2023-07-18 12:34:56', 'Project 4 description', 'Project 4 label', 3, 'f96a4e1a-08f8-4eaf-8f32-05492cf21725', '7b150ce9-122c-4ea3-87c5-233190d1f746', true),

  ('83544f2a-652d-4b5b-8834-d06cd496d360', '2023-07-18 12:34:56', 'Project 5 description', 'Project 5 label', 3, '292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', 'f3e5806b-17c5-4440-96c5-5720a912e48d', true),
  
  ('b14a488c-e7df-4cc2-a423-562a4bf3df7c', '2023-07-18 12:34:56', 'Project 6 description', 'Project 6 label', 2, '68fb1179-62d9-48f6-b6d8-683146fc739f', '7c79d37b-25cb-48e6-b2ac-8f4d7da9cc65', true);


INSERT INTO project_memeberships (users_id, projects_id)
VALUES
  ('08e32d91-d5c9-44f8-84fb-c9f4979c7531', '3d37f6c1-4b7b-404e-8e5d-649d90f8498c'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', '3d37f6c1-4b7b-404e-8e5d-649d90f8498c'),
  ('c75a2b32-98b3-45e0-91db-4f1c843d45f8', '3d37f6c1-4b7b-404e-8e5d-649d90f8498c'),

  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', 'd1488f27-25e1-4d03-af29-ee652dd658bb'),
  ('60393bc7-0fcd-46c7-8db0-4ee66a5de7e6', 'd1488f27-25e1-4d03-af29-ee652dd658bb'),
  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', 'd1488f27-25e1-4d03-af29-ee652dd658bb'),

  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', 'f202b048-aa24-4b6e-979e-69f2922544a5'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', 'f202b048-aa24-4b6e-979e-69f2922544a5'),

  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '8a189662-eb77-4b57-a930-60233f65bb5e'),
  ('f96a4e1a-08f8-4eaf-8f32-05492cf21725', '8a189662-eb77-4b57-a930-60233f65bb5e'),
  ('754a17aa-8353-4fc3-b1db-6a48211cd87b', '8a189662-eb77-4b57-a930-60233f65bb5e'),

  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', '83544f2a-652d-4b5b-8834-d06cd496d360'),
  ('c468e82e-07a0-4426-9b56-7d06d515e554', '83544f2a-652d-4b5b-8834-d06cd496d360'),
  ('e6431235-19d2-4e8a-943a-5130e5b744a8', '83544f2a-652d-4b5b-8834-d06cd496d360'),

  ('292aa3b9-07e9-4cf1-8d9c-74253c5a0b2b', 'b14a488c-e7df-4cc2-a423-562a4bf3df7c'),
  ('68fb1179-62d9-48f6-b6d8-683146fc739f', 'b14a488c-e7df-4cc2-a423-562a4bf3df7c');