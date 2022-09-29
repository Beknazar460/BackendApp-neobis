CREATE TABLE orders (
    id BIGSERIAL NOT NULl PRIMARY KEY,
    title_of_product VARCHAR(60) NOT NULL  UNIQUE,
    price_of_product VARCHAR(10) NOT NULL,
    users_id BIGSERIAL NOT NULL,
    laptops_id BIGSERIAL NOT NULL,
    FOREIGN KEY (users_id) REFERENCES users (id),
    FOREIGN KEY (laptops_id) REFERENCES laptops (id)
);