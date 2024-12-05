drop table if exists photo;

CREATE TABLE photo (
                       id serial PRIMARY KEY,
                       profileId BIGINT NOT NULL,
                       photoUrl VARCHAR(255) NOT NULL,
                       uploadDate TIMESTAMP NOT NULL,
                       FOREIGN KEY (profileId) REFERENCES profile(id)
);