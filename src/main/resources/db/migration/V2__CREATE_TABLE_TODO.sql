CREATE TABLE todo (
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title VARCHAR(66) NOT NULL,
    completed BOOLEAN NOT NULL,
    users_id BIGSERIAL NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users (id)
);