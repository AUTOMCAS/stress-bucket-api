drop database stressbucketdb;
drop user stressbucket;

create user stressbucket with password 'password';
create database stressbucketdb with template=template0 owner=stressbucket;
\connect stressbucketdb;

alter default privileges grant all on tables to stressbucket;
alter default privileges grant all on sequences to stressbucket;

create table buckets(
bucket_id integer primary key not null,
bucket_name varchar(20) not null,
stress_level integer not null
);

create table events(
event_id integer primary key not null,
bucket_id integer not null,
type varchar(20) not null,
description varchar(50) not null,
stress_level_change integer not null,
resulting_stress_level integer not null,
event_time_date bigint not null
);

alter table events add constraint cat_buckets_fk
foreign key (bucket_id) references buckets(bucket_id);

create sequence buckets_seq increment 1 start 1;
create sequence events_seq increment 1 start 1;