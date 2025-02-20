create database devices;

create table devices (
    id    serial primary key,
    name  varchar(255),
    price float
);

create table people (
    id   serial primary key,
    name varchar(255)
);

create table devices_people (
    id        serial primary key,
    device_id int references devices (id),
    people_id int references people (id)
);

insert into people(name) values ('Валера');
insert into people(name) values ('Никита');
insert into people(name) values ('Алена');
insert into people(name) values ('Максим');
insert into people(name) values ('Женя');

insert into devices(name, price) VALUES ('Iphone', 1199);
insert into devices(name, price) VALUES ('Samsung', 999);
insert into devices(name, price) VALUES ('MacBook', 3290);
insert into devices(name, price) VALUES ('Ipad', 1499);
insert into devices(name, price) VALUES ('Fridge', 650);
insert into devices(name, price) VALUES ('PS5', 780);

insert into devices_people(device_id, people_id) values (1, 1);
insert into devices_people(device_id, people_id) values (3, 1);
insert into devices_people(device_id, people_id) values (2, 2);
insert into devices_people(device_id, people_id) values (4, 2);
insert into devices_people(device_id, people_id) values (5, 2);
insert into devices_people(device_id, people_id) values (1, 3);
insert into devices_people(device_id, people_id) values (3, 3);
insert into devices_people(device_id, people_id) values (4, 3);
insert into devices_people(device_id, people_id) values (5, 3);
insert into devices_people(device_id, people_id) values (5, 4);
insert into devices_people(device_id, people_id) values (6, 4);
insert into devices_people(device_id, people_id) values (1, 5);
insert into devices_people(device_id, people_id) values (3, 5);
insert into devices_people(device_id, people_id) values (4, 5);
insert into devices_people(device_id, people_id) values (5, 5);
insert into devices_people(device_id, people_id) values (6, 5);

select avg(price) from devices;

select p.name, avg(price)
from devices d
join devices_people dp on dp.device_id = d.id
join people p on dp.people_id = p.id
group by p.name;

select p.name, avg(price)
from devices d
         join devices_people dp on dp.device_id = d.id
         join people p on dp.people_id = p.id
group by p.name
having avg(price) > 2000;
