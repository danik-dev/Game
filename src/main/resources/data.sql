-- THIS DATA IS ONLY FOR DEMONSTRATION PURPOSES. 
-- test user: mark password: Aa!12345

INSERT INTO USERS (id, username, password, email, algorithm, registration_date) VALUES (1004, 'mark', '$2a$10$..G3GP8akMKzUryFr9X/OeAfQ4Ncbm4U5Ns2zfQ9mVKJnwEck.eKe', 'mark@gmail.com', 'BCRYPT', '2023-05-07');
INSERT INTO STATISTICS (id, all_time_play_time, all_time_score, fastest_win, max_score, user_id) VALUES (1002, 380504, 7476, 21420, 1804, 1004);

INSERT INTO USERS (id, username, password, email, algorithm, registration_date) VALUES (1006, 'Buba', '$2a$10$ZfwMGDVxEEEd5U8VvSG2HeCKmidkdlWy.722H19e65GJbYRJ6PDcm', 'kjhgfdslkjhgfd@gmail.com', 'BCRYPT', '2023-05-09');
INSERT INTO STATISTICS (id, all_time_play_time, all_time_score, fastest_win, max_score, user_id) VALUES (1004, 2669213, 42828, 2028, 24248, 1006);

INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1001, '2023-05-08 19:10:00.000000', 16560, 432, 1002);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1002, '2023-05-08 19:15:00.000000', 21612, 860, 1002);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1003, '2023-05-09 01:15:00.000000', 158307, 1804, 1002);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1004, '2023-05-09 01:30:00.000000', 232677, 2420, 1004);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1005, '2023-05-09 01:35:00.000000', 952253, 14132, 1004);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1006, '2023-05-09 01:50:00.000000', 182922, 2028, 1004);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1007, '2023-05-09 01:55:00.000000', 1301361, 24248, 1004);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1008, '2023-05-09 02:50:00.000000', 31075, 960, 1002);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1009, '2023-05-09 02:55:00.000000', 32753, 768, 1002);
INSERT INTO GAME_SESSION (id, get_started, duration, score, statistics_id) VALUES (1010, '2023-05-09 03:00:00.000000', 24821, 744, 1002);