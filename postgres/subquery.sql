CREATE DATABASE sales;

CREATE TABLE customers
(
    id         serial primary key,
    first_name text,
    last_name  text,
    age        int,
    country    text
);

INSERT INTO customers(first_name, last_name, age, country) VALUES ('Aleksandr', 'Pryhodzich', 23, 'Belarus');
INSERT INTO customers(first_name, last_name, age, country) VALUES ('Vadim', 'Kizmich', 22, 'Poland');
INSERT INTO customers(first_name, last_name, age, country) VALUES ('Misha', 'Valuev', 22, 'Ukraine');
INSERT INTO customers(first_name, last_name, age, country) VALUES ('Anatoliy', 'Vasilenko', 50, 'Russia');
INSERT INTO customers(first_name, last_name, age, country) VALUES ('Andrey', 'Potapchuk', 23, 'Poland');

SELECT *
FROM customers
WHERE age = (SELECT min(age) FROM customers);

CREATE TABLE orders
(
    id          serial primary key,
    amount      int,
    customer_id int references customers (id)
);

INSERT INTO orders(amount, customer_id) VALUES (3, 1);
INSERT INTO orders(amount, customer_id) VALUES (1, 3);
INSERT INTO orders(amount, customer_id) VALUES (25, 4);
INSERT INTO orders(amount, customer_id) VALUES (7, 5);

SELECT *
FROM customers
WHERE id not IN (SELECT customer_id from orders)