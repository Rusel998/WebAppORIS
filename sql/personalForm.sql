drop table if exists personalForm;

CREATE TABLE personalform (
                              id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              userid BIGINT UNIQUE NOT NULL,
                              bio TEXT,
                              age INT,
                              gender VARCHAR(10),
                              FOREIGN KEY (userid) REFERENCES users (id)
);
ALTER TABLE personalform
    ADD COLUMN photo BYTEA;