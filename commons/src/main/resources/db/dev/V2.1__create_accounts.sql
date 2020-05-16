create table accounts (
    id varchar(10) primary key,
    email varchar(64) unique not null,
    password char(60) not null,
    role varchar(16)
);