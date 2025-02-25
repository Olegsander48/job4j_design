CREATE
    OR REPLACE PROCEDURE delete_data(u_id integer)
    LANGUAGE 'plpgsql'
AS
$$
BEGIN
    DELETE
    FROM products
    WHERE id = u_id;
END;
$$;

CALL delete_data(1);


CREATE
    OR REPLACE FUNCTION f_delete_data()
    RETURNS integer[]
    LANGUAGE 'plpgsql'
AS
$$
DECLARE
    result integer[] := '{}';
BEGIN
    SELECT ARRAY_AGG(id)
    INTO result
    FROM products
    WHERE count = 0;
    DELETE
    FROM products
    WHERE count = 0;
    RETURN result;
END;
$$;

INSERT INTO products(name, producer, count, price)
VALUES ('product_4', 'producer_4', 0, 3000);
INSERT INTO products(name, producer, count, price)
VALUES ('product_5', 'producer_5', 0, 1000);
SELECT f_delete_data();