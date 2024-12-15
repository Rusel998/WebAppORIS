drop table if exists complaint;

CREATE TABLE complaint (
                           id serial PRIMARY KEY,
                           complainantid BIGINT NOT NULL, -- кто жалуется
                           personalFormId BIGINT NOT NULL,    -- на какую анкету жалуются
                           reason TEXT NOT NULL,
                           datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                           FOREIGN KEY (complainantid) REFERENCES users (id),
                           FOREIGN KEY (personalFormId) REFERENCES personalform (id)
);