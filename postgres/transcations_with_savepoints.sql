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

start transaction;
savepoint first_savepoint;
update car set model = 'Mercedes c63 amg black series' where power = 510;
select * from car;
savepoint second_savepoint;
insert into car(model, seats_count, power) values ('BMW m5', 5, 625);
select * from car;
rollback to second_savepoint;
select * from car;
rollback to first_savepoint;
select * from car;
