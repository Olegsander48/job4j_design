create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('iphone', 100000);
insert into devices(name, price) values ('notebook', 120000);
insert into devices(name, price) values ('watches', 40000);
insert into devices(name, price) values ('headphones', 20000);

insert into people(name) values ('Aleksandr');
insert into people(name) values ('Vladimir');
insert into people(name) values ('Ivan');
insert into people(name) values ('Andrey');

insert into devices_people(device_id, people_id) values (2, 1);
insert into devices_people(device_id, people_id) values (4, 1);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (3, 4);

select avg(price) as "—редн€€ цена устройств" from devices;

select p.name, avg(d.price) 
from devices as d
join devices_people dp 
on dp.device_id = d.id 
join people as p
on dp.people_id = p.id
group by p.name;

select p.name, avg(d.price) 
from devices as d
join devices_people dp 
on dp.device_id = d.id 
join people as p
on dp.people_id = p.id
group by p.name
having avg(d.price) > 50000;