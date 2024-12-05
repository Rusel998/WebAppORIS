drop table if exists complaint;

CREATE TABLE complaint (
                           id serial PRIMARY KEY,
                           complainantId BIGINT NOT NULL,
                           offenderId BIGINT NOT NULL,
                           reason TEXT NOT NULL,
                           comment TEXT,
                           dateTime TIMESTAMP NOT NULL,
                           FOREIGN KEY (complainantId) REFERENCES users(id),
                           FOREIGN KEY (offenderId) REFERENCES users(id)
);