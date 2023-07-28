-- ----------------------------
-- Records of status
-- ----------------------------


INSERT INTO status (id, createdOn, label, description, project_board_id, user_board_id)
VALUES
  ('uuid_status1', '2023-07-18 12:34:56', 'COMPLETED', 'Status 1 description', 'uuid_project_board1', NULL),
  ('uuid_status2', '2023-07-18 12:34:56', 'IN PROGRESS', 'Status 2 description', 'uuid_project_board1', NULL),
  ('uuid_status3', '2023-07-18 12:34:56', 'ENDED', 'Status 3 description', 'uuid_project_board1', NULL),
  ('uuid_status4', '2023-07-18 12:34:56', 'ISSUE', 'Status 4 description', 'uuid_project_board1', NULL),


  ('uuid_status5', '2023-07-18 12:34:56', 'COMPLETED', 'Status 5 description', 'uuid_project_board2', NULL),
  ('uuid_status6', '2023-07-18 12:34:56', 'ENDED', 'Status 6 description', 'uuid_project_board2', NULL),


  ('uuid_status7', '2023-07-18 12:34:56', 'STARTED', 'Status 7 description', 'uuid_project_board3', NULL),
  ('uuid_status8', '2023-07-18 12:34:56', 'FINISHED', 'Status 8 description', 'uuid_project_board3', NULL),
  ('uuid_status9', '2023-07-18 12:34:56', 'MATURITY', 'Status 9 description', 'uuid_project_board3', NULL),
  ('uuid_status10', '2023-07-18 12:34:56', 'CLOSED', 'Status 10 description', 'uuid_project_board3', NULL),


  ('uuid_status11', '2023-07-18 12:34:56', 'STARTED', 'Status 11 description', 'uuid_project_board4', NULL),
  ('uuid_status12', '2023-07-18 12:34:56', 'FINISHED', 'Status 12 description', 'uuid_project_board4', NULL),


  ('uuid_status13', '2023-07-18 12:34:56', 'ONGOING', 'Status 13 description', NULL, 'uuid_user_board1'),
  ('uuid_status14', '2023-07-18 12:34:56', 'FINISHED', 'Status 14 description', NULL, 'uuid_user_board1'),
  ('uuid_status15', '2023-07-18 12:34:56', 'ABANDONED', 'Status 15 description', NULL, 'uuid_user_board1'),
  ('uuid_status16', '2023-07-18 12:34:56', 'STOPPED', 'Status 16 description', NULL, 'uuid_user_board1'),
  ('uuid_status17', '2023-07-18 12:34:56', 'CLOSED', 'Status 17 description', NULL, 'uuid_user_board1'),

  ('uuid_status18', '2023-07-18 12:34:56', 'ONGOING', 'Status 18 description', NULL, 'uuid_user_board2'),
  ('uuid_status19', '2023-07-18 12:34:56', 'STOPPED', 'Status 19 description', NULL, 'uuid_user_board2'),
  ('uuid_status20', '2023-07-18 12:34:56', 'FINISHED', 'Status 20 description', NULL, 'uuid_user_board2'),
  ('uuid_status21', '2023-07-18 12:34:56', 'CLOSED', 'Status 21 description', NULL, 'uuid_user_board2'),
  ('uuid_status22', '2023-07-18 12:34:56', 'ABANDONED', 'Status 22 description', NULL, 'uuid_user_board2'),
  
  ('uuid_status', '2023-07-18 12:34:56', 'ONGOING', 'Status 18 description', NULL, 'uuid_user_board2'),
  ('uuid_status19', '2023-07-18 12:34:56', 'STOPPED', 'Status 19 description', NULL, 'uuid_user_board2'),
  ('uuid_status20', '2023-07-18 12:34:56', 'FINISHED', 'Status 20 description', NULL, 'uuid_user_board2'),
  ('uuid_status21', '2023-07-18 12:34:56', 'CLOSED', 'Status 21 description', NULL, 'uuid_user_board2'),
  ('uuid_status22', '2023-07-18 12:34:56', 'ABANDONED', 'Status 22 description', NULL, 'uuid_user_board2'),
;
