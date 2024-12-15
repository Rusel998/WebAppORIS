drop table if exists users;

CREATE TABLE users (
                       id SERIAL PRIMARY KEY ,
                       username VARCHAR(255) NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password TEXT NOT NULL,
                       role varchar(20) default 'member'
);