drop table if exists personalForm;

CREATE TABLE personalform (
                              id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                              userid BIGINT UNIQUE NOT NULL,
                              bio TEXT,
                              age INT,
                              birthdate DATE,
                              gender VARCHAR(10),
                              profileviews INT DEFAULT 0,
                              FOREIGN KEY (userid) REFERENCES users (id)
);
