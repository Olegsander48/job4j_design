create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna(name, avg_age, discovery_date) values ('anfisher', 5, '2000-06-15');
insert into fauna(name, avg_age, discovery_date) values ('king', 11000, '1500-11-01');
insert into fauna(name, avg_age, discovery_date) values ('heck', 16000, '1700-01-01');
insert into fauna(name, avg_age, discovery_date) values ('angle-fish', 5, null);

select * from fauna 
where name LIKE '%fish%';

select * from fauna
where avg_age between 10000 and 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';