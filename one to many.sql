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