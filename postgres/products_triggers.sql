CREATE
    OR REPLACE FUNCTION tax_after()
    RETURNS trigger AS
$$
BEGIN
    UPDATE products
    SET price = price + price * 0.2
    WHERE id = (SELECT id FROM inserted);
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tax_after_trigger
    AFTER INSERT
    ON products
    REFERENCING new TABLE AS
        inserted
    FOR EACH STATEMENT
EXECUTE PROCEDURE tax_after();

INSERT INTO products(name, producer, count, price)
VALUES ('product_3', 'producer_3', 15, 200);
INSERT INTO products(name, producer, count, price)
VALUES ('product_4', 'producer_4', 47, 300);


CREATE
    OR REPLACE FUNCTION tax_before()
    RETURNS trigger AS
$$
BEGIN
    new.price = new.price + new.price * 0.13;
    RETURN new;
END;
$$
    LANGUAGE 'plpgsql';

CREATE TRIGGER tax_before_trigger
    BEFORE INSERT
    ON products
    FOR EACH ROW
EXECUTE PROCEDURE tax_before();

INSERT INTO products(name, producer, count, price)
VALUES ('product_3', 'producer_3', 15, 200);

