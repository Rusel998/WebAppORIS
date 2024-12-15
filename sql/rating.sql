drop table if exists rating;

CREATE TABLE rating (
                        id serial PRIMARY KEY,
                        userid BIGINT NOT NULL,         -- кто оценивает
                        rateduserid BIGINT NOT NULL,    -- кого оценивают
                        rating INT NOT NULL CHECK (rating BETWEEN 1 AND 5),
                        comment TEXT,
                        date DATE DEFAULT CURRENT_DATE,
                        FOREIGN KEY (userid) REFERENCES users (id),
                        FOREIGN KEY (rateduserid) REFERENCES users (id)
);