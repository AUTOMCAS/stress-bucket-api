DROP DATABASE stress_bucket_db;
DROP USER stressbucket;

CREATE USER stressbucket WITH password 'password';
CREATE database stress_bucket_db WITH template=template0 owner=stressbucket;
\CONNECT stress_bucket_db;

ALTER DEFAULT PRIVILEGES GRANT ALL ON tables TO stressbucket;
ALTER DEFAULT PRIVILEGES GRANT ALL ON sequences TO stressbucket;


CREATE TABLE  buckets(
id SERIAL PRIMARY KEY,
name VARCHAR(20) NOT NULL,
stress_level INTEGER NOT NULL
);

CREATE TABLE events(
id SERIAL PRIMARY KEY,
bucket_id INTEGER NOT NULL,
stress_type VARCHAR(20) NOT NULL,
description VARCHAR(50) NOT NULL,
stress_level_change INTEGER NOT NULL,
resulting_stress_level INTEGER NOT NULL,
date_time TIMESTAMP NOT NULL,
FOREIGN KEY (bucket_id) REFERENCES buckets (id)
);



CREATE sequence buckets_seq increment 1 start 1;
CREATE sequence events_seq increment 1 start 1;