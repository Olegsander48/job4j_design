create table fauna
(
    id             serial primary key,
    name           text,
    avg_age        int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) VALUES ('Cat', 15, null);
insert into fauna(name, avg_age, discovery_date) VALUES ('Dog', 23, null);
insert into fauna(name, avg_age, discovery_date) VALUES ('Parrot', 60, '01.01.1490');
insert into fauna(name, avg_age, discovery_date) VALUES ('Fellyfish', 1, '01.01.1874');
insert into fauna(name, avg_age, discovery_date) VALUES ('Glass sponges', 15000, null);

select * from fauna where name like '%fish%';
select * from fauna where avg_age between 10000 and 21000;
select * from fauna where discovery_date is null;
select * from fauna WHERE EXTRACT(YEAR FROM discovery_date) > 1450;