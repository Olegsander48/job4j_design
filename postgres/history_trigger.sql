CREATE TABLE history_of_price (
    id    serial PRIMARY KEY,
    name  varchar(50),
    price integer,
    date  timestamp
);

CREATE
    OR REPLACE FUNCTION insert_to_history()
    RETURNS trigger AS
$$
BEGIN
    INSERT INTO history_of_price(name, price, date) VALUES (new.name, new.price, CURRENT_TIMESTAMP);
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER insert_into_products
    AFTER INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE insert_to_history();

INSERT INTO products(name, producer, count, price)
VALUES ('product_4', 'producer_4', 47, 300);