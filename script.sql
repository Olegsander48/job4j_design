create table Automobile (
    id serial primary key,
    brand text,
    price int,
    manual boolean
);

insert into automobile(brand, manual, price) values ('Mercedes', true, 7000);
select * from automobile;

update automobile set brand = 'BMW';
select * from automobile;

delete from automobile;
select * from automobile;