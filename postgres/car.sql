create database automobile;
create table car(
                    id serial primary key,
                    model varchar(255),
                    engine text,
                    horsePowers int
);
insert into car(model, engine, horsePowers)  values('Mercedes', 'v8', 612);
update car set model = 'Mercedes-Benz';
insert into car(model, engine, horsePowers)  values('BMW', 'v12', 576);
delete from car;