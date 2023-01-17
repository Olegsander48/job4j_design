create table type(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    type_id int references type(id),
    expired_date date,
    price float
);

insert into "type"(name) values ('cheese');
insert into "type"(name) values ('milk');
insert into "type"(name) values ('meet');
insert into "type"(name) values ('chocolate');

insert into product(name, type_id, expired_date, price) values ('cheese mozzarella', 1, '2023-01-30', 6.30);
insert into product(name, type_id, expired_date, price) values ('processed cheese', 1, '2021-04-07', 2.30);
insert into product(name, type_id, expired_date, price) values ('milk 3%', 2, '2023-01-30', 2.00);
insert into product(name, type_id, expired_date, price) values ('ice cream flavored milkshake', 2, '2023-01-30', 1.89);
insert into product(name, type_id, expired_date, price) values ('chicken thighs', 3, '2023-02-09', 10.50);
insert into product(name, type_id, expired_date, price) values ('steak', 3, '2023-07-09', 25.00);
insert into product(name, type_id, expired_date, price) values ('bitter chocolate', 4, '2024-01-01', 25.00);
insert into product(name, type_id, expired_date, price) values ('bitter chocolate', 4, '2024-01-01', 3.00);

select product.name, product.expired_date, product.price, type.name from product, "type"
where product.type_id = type.id and type.name = 'cheese';

select * from product
where name LIKE '%ice cream%';

select * from product
where product.expired_date < current_date; 

select * from product
where price = (select max(price) from product);

select type.name, count(*) from product, "type"
where product.type_id = "type".id
group by type.name;

select product.name, product.expired_date, product.price, type.name from product, "type"
where product.type_id = type.id and (type.name = 'cheese' or type.name = 'milk'); 

select count(product.name), type.name from product, "type"
where product.type_id = "type".id
group by type.name
having count(product.name) < 10;

select product.name, product.expired_date, product.price, type.name from product, "type"
where product.type_id = type.id;