--tables for many-to-one
create table car
(
    id        serial primary key,
    model     varchar(255),
    engine_id int references engine (id)
);

create table engine
(
    id          serial primary key,
    horsePowers : int
);

--tables for one-to-one
create table car
(
    id        serial primary key,
    model     varchar(255),
    engine_id int references vin(id) unique
);

create table vin
(
    id serial primary key,
    number : text
);

--tables for many-to-many
create table car
(
    id        serial primary key,
    model     varchar(255)
);

create table driver
(
    id serial primary key,
    name : varchar(255),
    experience : int
);

create table driver_car
(
    id serial primary key,
    car_id int references car(id),
    driver_id int references driver(id)
);