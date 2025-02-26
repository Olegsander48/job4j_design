create table car (
    id serial primary key,
    model text,
    seats_count int,
    power int
);

insert into car(model, seats_count, power) values ('Mercedes c63', 4, 510);
insert into car(model, seats_count, power) values ('Mercedes GLS', 7, 385);
insert into car(model, seats_count, power) values ('Mercedes Sprinter', 18, 190);
insert into car(model, seats_count, power) values ('Volkswagen passat', 5, 220);
insert into car(model, seats_count, power) values ('Toyota camry', 5, 249);

begin transaction;
begin transaction isolation level repeatable read;
update car set model = 'Mercedes c63 amg' where power = 510;
begin transaction isolation level serializable;
update car set model = 'Mercedes c63 amg' where power = 510;
