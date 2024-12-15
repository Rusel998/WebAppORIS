DROP TABLE IF EXISTS interests;
CREATE TABLE interests (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS user_interests;
CREATE TABLE user_interests (
                                id SERIAL PRIMARY KEY,
                                formId BIGINT NOT NULL,
                                interestid BIGINT NOT NULL,
                                FOREIGN KEY (formId) REFERENCES personalform (id),
                                FOREIGN KEY (interestid) REFERENCES interests (id),
                                UNIQUE (formId, interestid)
);