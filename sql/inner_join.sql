create table engine (
    id serial primary key,
    power int
);

create table automobile (
    id serial primary key,
    brand text,
    price int,
    manual boolean,
    engine_id int references engine(id)
);

insert into engine(power) values (550);
insert into engine(power) values (249);
insert into engine(power) values (339);
insert into engine(power) values (510);
insert into engine(power) values (130);

insert into automobile(brand, price, manual, engine_id) values('land rover', 100000, false, 1); 
insert into automobile(brand, price, manual, engine_id) values('land rover', 50000, false, 3); 
insert into automobile(brand, price, manual, engine_id) values('land rover', 60000, false, 2); 
insert into automobile(brand, price, manual, engine_id) values('land rover', 18000, false, 4);
insert into automobile(brand, price, manual, engine_id) values('land rover', 70000, true , 5);

select au.brand, au.price, au.manual, en."power" from automobile as au 
join engine as en on au.engine_id = en."id";

select au.brand as "Бренд автомобиля", au.price as "Цена автомобиля", en."power" as "Мощность автомобиля" from automobile as au 
join engine as en on au.engine_id = en."id";

select au.brand, au.price, au.manual, en."power" from automobile as au 
join engine as en on au.engine_id = en."id" and au.manual = true;

