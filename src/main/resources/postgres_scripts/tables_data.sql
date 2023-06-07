INSERT INTO public.users (id, username, password, email, algorithm, registration_date) VALUES (4, 'mark', '$2a$10$..G3GP8akMKzUryFr9X/OeAfQ4Ncbm4U5Ns2zfQ9mVKJnwEck.eKe', 'mark@gmail.com', 'BCRYPT', '2023-05-07');
INSERT INTO public.users (id, username, password, email, algorithm, registration_date) VALUES (6, 'Buba', '$2a$10$ZfwMGDVxEEEd5U8VvSG2HeCKmidkdlWy.722H19e65GJbYRJ6PDcm', 'kjhgfdslkjhgfd@gmail.com', 'BCRYPT', '2023-05-09');

INSERT INTO public.statistics (id, all_time_play_time, all_time_score, fastest_win, user_id, max_score) VALUES (4, 2669213, 42828, 2028, 6, 24248);
INSERT INTO public.statistics (id, all_time_play_time, all_time_score, fastest_win, user_id, max_score) VALUES (2, 380504, 7476, 21420, 4, 1804);

INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (1, '2023-05-08 19:10:00.000000', 16560, 432, 2);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (2, '2023-05-08 19:15:00.000000', 21612, 860, 2);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (3, '2023-05-09 01:15:00.000000', 158307, 1804, 2);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (4, '2023-05-09 01:30:00.000000', 232677, 2420, 4);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (5, '2023-05-09 01:35:00.000000', 952253, 14132, 4);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (6, '2023-05-09 01:50:00.000000', 182922, 2028, 4);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (7, '2023-05-09 01:55:00.000000', 1301361, 24248, 4);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (8, '2023-05-09 02:50:00.000000', 31075, 960, 2);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (9, '2023-05-09 02:55:00.000000', 32753, 768, 2);
INSERT INTO public.game_session (id, get_started, duration, score, statistics_id) VALUES (10, '2023-05-09 03:00:00.000000', 24821, 744, 2);