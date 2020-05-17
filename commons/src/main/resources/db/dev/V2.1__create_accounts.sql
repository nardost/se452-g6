create table accounts (
    id char(10) primary key,
    email varchar(64) unique not null,
    password char(60) not null,
    role varchar(16) not null
);
