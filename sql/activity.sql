drop table if exists activity;

CREATE TABLE activity (
                          id serial PRIMARY KEY,
                          userId BIGINT NOT NULL,
                          lastLogin TIMESTAMP NOT NULL,
                          profileViews INT NOT NULL,
                          FOREIGN KEY (userId) REFERENCES users(id)
);