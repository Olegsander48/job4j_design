create table type (
    id   serial primary key,
    name varchar(255)
);

create table product (
    id           serial primary key,
    name         varchar(255),
    type_id      int references type (id),
    expired_date date,
    price        float
);

insert into type(name)
values ('Сыр');
insert into type(name)
values ('Молоко');
insert into type(name)
values ('Мясо');
insert into type(name)
values ('Колбаса');
insert into type(name)
values ('Хлеб');
insert into type(name)
values ('Крупы');

insert into product(name, type_id, expired_date, price)
VALUES ('Моцарелла', 1, '24.04.2025', 6.75);
insert into product(name, type_id, expired_date, price)
VALUES ('Гауда', 1, '28.07.2025', 11.45);
insert into product(name, type_id, expired_date, price)
VALUES ('Сливочный', 1, '22.02.2025', 3.40);
insert into product(name, type_id, expired_date, price)
VALUES ('2%', 2, '20.02.2025', 2.15);
insert into product(name, type_id, expired_date, price)
VALUES ('3%', 2, '24.02.2025', 3.45);
insert into product(name, type_id, expired_date, price)
VALUES ('5%', 2, '16.02.2025', 1.99);
insert into product(name, type_id, expired_date, price)
VALUES ('Свинина', 3, '05.05.2025', 11.99);
insert into product(name, type_id, expired_date, price)
VALUES ('Говядина', 3, '04.03.2025', 15.99);
insert into product(name, type_id, expired_date, price)
VALUES ('Полянвица', 3, '01.03.2025', 17.99);
insert into product(name, type_id, expired_date, price)
VALUES ('Хамон', 3, '01.03.2026', 20);
insert into product(name, type_id, expired_date, price)
VALUES ('Бородинский', 5, '27.02.2025', 3.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Батон', 5, '29.09.2025', 2.00);
insert into product(name, type_id, expired_date, price)
VALUES ('Багет', 5, '01.03.2025', 5.40);
insert into product(name, type_id, expired_date, price)
VALUES ('Гречка', 6, '21.05.2025', 5.10);
insert into product(name, type_id, expired_date, price)
VALUES ('Рис', 6, '24.08.2025', 4.45);
insert into product(name, type_id, expired_date, price)
VALUES ('Булгур', 6, '24.08.2026', 6.78);
insert into product(name, type_id, expired_date, price)
VALUES ('Мороженное 20 копеек', 1, '24.08.2026', 20);
insert into product(name, type_id, expired_date, price)
VALUES ('Мороженное коровка', 1, '24.08.2027', 5.45);
insert into product(name, type_id, expired_date, price)
VALUES ('Мороженное Аленка', 1, '21.04.2024', 7.55);

select p.name, p.price, p.expired_date
from product p
         join type t on t.id = p.type_id
where t.name = 'Сыр';

select p.name, p.price, p.expired_date
from product p
where p.name LIKE '%Мороженное%';

select p.name, p.price, p.expired_date
from product p
where p.expired_date <= '20.02.2025';

select p.price, t.name
from product p
         join type t on t.id = p.type_id
group by t.name, p.price
having p.price = (select max(price) from product);


select t.name, count(p.name)
from product p
         join type t on t.id = p.type_id
group by t.name;

select p.name, p.price, p.expired_date
from product p
         join type t on t.id = p.type_id
where t.name = 'Сыр'
   or t.name = 'Молоко';

select t.name
from product p
         join type t on t.id = p.type_id
group by t.name
having count(p.name) < 10;

select p.name, p.price, p.expired_date, t.name
from product p
         join type t on t.id = p.type_id;