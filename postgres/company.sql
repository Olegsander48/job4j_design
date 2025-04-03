CREATE TABLE company (
    id   integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person (
    id         integer NOT NULL,
    name       character varying,
    company_id integer REFERENCES company (id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO company (id, name)
VALUES (1, 'Apple'),
       (2, 'Bosch'),
       (3, 'Volkswagen'),
       (4, 'IBM'),
       (5, 'Google'),
       (6, 'Microsoft');

INSERT INTO person (id, name, company_id)
VALUES (1, 'Aleksey', 1),
       (2, 'Vladimir', 1),
       (3, 'Misha', 1),
       (4, 'Yaroslav', 1),
       (5, 'Natasha', 2),
       (6, 'Vadim', 3),
       (7, 'Anatoliy', 4),
       (8, 'Anna', 4),
       (9, 'Ignat', 4),
       (10, 'Aleksandr', 4),
       (11, 'Yulia', 5),
       (12, 'Valentina', 5),
       (13, 'Ivan', 6);

SELECT p.name, c.name
FROM person p
         JOIN company c ON p.company_id = c.id
WHERE company_id != 5;

SELECT c.name, COUNT(company_id)
FROM person p
         JOIN company c ON p.company_id = c.id
GROUP BY 1
HAVING COUNT(company_id) = (SELECT COUNT(company_id)
                            FROM person
                            GROUP BY company_id
                            ORDER BY 1 DESC
                            LIMIT 1);