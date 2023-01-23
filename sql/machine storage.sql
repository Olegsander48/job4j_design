create table car_bodies(
    id serial primary key,
    name varchar(30)
);

create table car_engines(
    id serial primary key,
    name varchar(30)
);

create table car_transmissions(
    id serial primary key,
    name varchar(30)
);

create table cars(
    id serial primary key,
    name varchar(30),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('седан');
insert into car_bodies(name) values ('универсал');
insert into car_bodies(name) values ('кабриолет');
insert into car_bodies(name) values ('пикап');

insert into car_engines(name) values ('i4');
insert into car_engines(name) values ('i6');
insert into car_engines(name) values ('v8');
insert into car_engines(name) values ('v4');

insert into car_transmissions(name) values ('mkpp');
insert into car_transmissions(name) values ('akpp');
insert into car_transmissions(name) values ('robot');

insert into cars(name, body_id, engine_id, transmission_id) values ('mercedes', 3, 2, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('bmw', 1, 3, 2);
insert into cars(name, body_id, engine_id, transmission_id) values ('audi', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) values ('buick', 1, null , 1);

select c.id, c.name, cb.name, ce.name, ct.name 
from cars as c 
left join car_bodies cb 
    on c.body_id = cb.id 
left join car_engines as ce 
    on c.engine_id = ce.id
left join car_transmissions as ct 
    on c.transmission_id = ct.id;

select car_bodies.name from car_bodies 
left join cars 
    on car_bodies.id = cars.body_id 
    where cars.body_id is null;
    
select car_engines.name from car_engines 
left join cars 
    on car_engines.id = cars.engine_id 
    where cars.engine_id is null;

select car_transmissions.name from car_transmissions 
left join cars 
    on car_transmissions.id = cars.transmission_id 
    where cars.transmission_id is null;