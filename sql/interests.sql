DROP TABLE IF EXISTS interests;
CREATE TABLE interests (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE
);
DROP TABLE IF EXISTS user_interests;
CREATE TABLE user_interests (
                                id SERIAL PRIMARY KEY,
                                userId BIGINT NOT NULL,
                                interestId BIGINT NOT NULL,
                                FOREIGN KEY (userId) REFERENCES users(id),
                                FOREIGN KEY (interestId) REFERENCES interests(id)
);
INSERT INTO interests (name) VALUES ('Программирование'), ('Игры'), ('Музыка');