DROP DATABASE stressbucket_db;
DROP USER stressbucket;

CREATE USER stressbucket WITH password 'password';
CREATE database stressbucket_db WITH template=template0 owner=stressbucket;
\CONNECT stressbucket_db;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO stressbucket;
ALTER DEFAULT PRIVILEGES GRANT ALL ON sequences TO stressbucket;

CREATE TABLE users(
user_id SERIAL PRIMARY KEY,
username VARCHAR(20) NOT NULL,
password text NOT NULL
);

CREATE TABLE buckets(
bucket_id SERIAL PRIMARY KEY,
user_id INTEGER NOT NULL,
name VARCHAR(20) NOT NULL,
stress_level INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (user_id)
);

CREATE TABLE events(
event_id SERIAL PRIMARY KEY,
user_id INTEGER NOT NULL,
bucket_id INTEGER NOT NULL,
stress_type VARCHAR(20) NOT NULL,
description VARCHAR(50) NOT NULL,
stress_level_change INTEGER NOT NULL,
resulting_stress_level INTEGER NOT NULL,
date_time TIMESTAMP NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (user_id),
FOREIGN KEY (bucket_id) REFERENCES buckets (bucket_id)
);
