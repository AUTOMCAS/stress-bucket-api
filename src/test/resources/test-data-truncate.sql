TRUNCATE users RESTART IDENTITY CASCADE;
TRUNCATE events RESTART IDENTITY CASCADE
ALTER SEQUENCE events_seq RESTART WITH 1;