create table car_bodies(
    id serial primary key ,
    name varchar(255)
);

create table car_engines(
    id serial primary key ,
    name varchar(255)
);

create table car_transmissions(
    id serial primary key ,
    name varchar(255)
);

create table cars(
    id serial primary key ,
    name varchar(255),
    body_id int references car_bodies(id),
    engine_id int references car_engines(id),
    transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('Sedan');
insert into car_bodies(name) values ('SUV');
insert into car_bodies(name) values ('Wagon');
insert into car_bodies(name) values ('Limousine');
insert into car_bodies(name) values ('Coupe');
insert into car_bodies(name) values ('Roadster');

insert into car_engines(name) values ('v8');
insert into car_engines(name) values ('v10');
insert into car_engines(name) values ('v12');
insert into car_engines(name) values ('v6');
insert into car_engines(name) values ('i6');

insert into car_transmissions(name) values ('Automatic');
insert into car_transmissions(name) values ('Manual');
insert into car_transmissions(name) values ('DSG');

insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Bmw m5 f90', 1, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Ranger Rover SVR', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Mercedes GLS', 2, 1, 1);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Audi rs6 c6', 3, 2, 1);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('BMW m5 e64', 1, 2, 3);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Porsche 911', 5, 4, 3);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Mercedes-Maybach', 4, 3, 1);
insert into cars(name, body_id, engine_id, transmission_id) VALUES ('Lotus elise', 5, null, 3);
