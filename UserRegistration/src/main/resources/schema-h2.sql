
drop table if exists users;
create table users (
    id VARCHAR(30),
    username VARCHAR(30),
    password VARCHAR(256),
    phone VARCHAR(20),
    address VARCHAR(4096),
    salary INTEGER,
    membertype VARCHAR(30)
);
