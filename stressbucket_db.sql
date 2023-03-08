DROP DATABASE stressbucket_db;
DROP USER stressbucket;

CREATE USER stressbucket WITH password 'password';
CREATE database stressbucket_db WITH template=template0 owner=stressbucket;
\CONNECT stressbucket_db;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO stressbucket;
ALTER DEFAULT PRIVILEGES GRANT ALL ON sequences TO stressbucket;

CREATE TABLE users(
id SERIAL PRIMARY KEY,
username VARCHAR(20) NOT NULL,
password text NOT NULL
);


CREATE TABLE buckets(
id SERIAL PRIMARY KEY,
user_id INTEGER NOT NULL,
name VARCHAR(20) NOT NULL,
stress_level INTEGER NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE events(
id SERIAL PRIMARY KEY,
user_id INTEGER NOT NULL,
bucket_id INTEGER NOT NULL,
stress_type VARCHAR(20) NOT NULL,
description VARCHAR(50) NOT NULL,
stress_level_change INTEGER NOT NULL,
resulting_stress_level INTEGER NOT NULL,
date_time TIMESTAMP NOT NULL,
FOREIGN KEY (user_id) REFERENCES users (id),
FOREIGN KEY (bucket_id) REFERENCES buckets (id)
);


CREATE sequence users_seq increment 1 start 1;
CREATE sequence buckets_seq increment 1 start 1;
CREATE sequence events_seq increment 1 start 1;