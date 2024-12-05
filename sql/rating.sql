drop table if exists rating;

CREATE TABLE rating (
                        id serial PRIMARY KEY,
                        userId BIGINT NOT NULL,
                        ratedUserId BIGINT NOT NULL,
                        rating INT NOT NULL,
                        comment TEXT,
                        FOREIGN KEY (userId) REFERENCES users(id),
                        FOREIGN KEY (ratedUserId) REFERENCES users(id)
);