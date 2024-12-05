drop table if exists users;

CREATE TABLE users (
                      id serial PRIMARY KEY,
                      username VARCHAR(255) NOT NULL UNIQUE,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password TEXT NOT NULL
);