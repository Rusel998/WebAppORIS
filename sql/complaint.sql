drop table if exists complaint;

CREATE TABLE complaint (
                           id serial PRIMARY KEY,
                           complainantid BIGINT NOT NULL, -- кто жалуется
                           offenderId BIGINT NOT NULL,    -- на какую анкету жалуются
                           reason TEXT NOT NULL,
                           datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           status VARCHAR(50) default 'pending',
                           FOREIGN KEY (complainantid) REFERENCES users (id),
                           FOREIGN KEY (offenderId) REFERENCES users (id)
);