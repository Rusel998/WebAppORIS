drop table if exists profile;

CREATE TABLE profile (
                         id serial PRIMARY KEY,
                         userId BIGINT NOT NULL,
                         bio TEXT,
                         age INT,
                         birthDate DATE,
                         gender VARCHAR(10),
                         FOREIGN KEY (userId) REFERENCES users(id)
);
