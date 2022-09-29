CREATE TABLE laptops (
                        id BIGSERIAL NOT NULl PRIMARY KEY,
                        title VARCHAR(60) NOT NULL  UNIQUE,
                        price VARCHAR(10) NOT NULL
);