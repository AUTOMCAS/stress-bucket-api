INSERT INTO users (username, password) VALUES ('testuser', 'password');
INSERT INTO buckets (user_id, name, stress_level) VALUES (1, 'Test bucket', 50);

INSERT INTO events (user_id, bucket_id, stress_type, description, stress_level_change, date_time) VALUES (1, 1, 'Test bucket', 'stress', 50);

INSERT INTO EVENTS(USER_ID, BUCKET_ID, STRESS_TYPE, DESCRIPTION, DATE_TIME, STRESS_LEVEL_CHANGE, RESULTING_STRESS_LEVEL) VALUES(1, 1, 'Test bucket', 'stress', '2021-08-09 13:57:40', 50, 60);