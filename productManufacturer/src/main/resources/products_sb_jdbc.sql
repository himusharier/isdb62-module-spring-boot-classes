CREATE TABLE products_sb_jdbc (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    price DECIMAL(10, 2),
    quantity INT,
    model VARCHAR(255)
);

SELECT * FROM products_sb_jdbc;