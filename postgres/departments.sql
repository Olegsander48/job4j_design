create table departments (
    id   serial primary key,
    name varchar(255)
);

create table employees (
    id             serial primary key,
    name           varchar(255),
    departments_id int references departments (id)
);

insert into departments(name)
values ('IT');
insert into departments(name)
values ('HR');
insert into departments(name)
values ('Managers');
insert into departments(name)
values ('Logistic');
insert into departments(name)
values ('Sales');
insert into departments(name)
values ('Direction');


insert into employees(name, departments_id)
VALUES ('Vanya', 1);
insert into employees(name, departments_id)
VALUES ('Sveta', 1);
insert into employees(name, departments_id)
VALUES ('Misha', 1);
insert into employees(name, departments_id)
VALUES ('Ekaterina', 2);
insert into employees(name, departments_id)
VALUES ('Valeria', 2);
insert into employees(name, departments_id)
VALUES ('Anastasia', 2);
insert into employees(name, departments_id)
VALUES ('Evgeniy', 3);
insert into employees(name, departments_id)
VALUES ('Anatoliy', 3);
insert into employees(name, departments_id)
VALUES ('Yulia', 4);
insert into employees(name, departments_id)
VALUES ('Natasha', 4);
insert into employees(name, departments_id)
VALUES ('Anton', 4);
insert into employees(name, departments_id)
VALUES ('Andrey', 4);
insert into employees(name, departments_id)
VALUES ('Vadim', 4);
insert into employees(name, departments_id)
VALUES ('Kamila', null);

select departments.name
from departments
         left join employees e on departments.id = e.departments_id
where e.departments_id is null;

select departments.name, e.name
from departments
         full join employees e on departments.id = e.departments_id;

select departments.name, e.name
from departments
         left join employees e on departments.id = e.departments_id;

select d.name, e.name
from employees e
         right join departments d on e.departments_id = d.id;

select departments.name, e.name
from departments
         cross join employees e;

create table teens(
    id serial primary key,
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) VALUES ('Misha', 'M');
insert into teens(name, gender) VALUES ('Vanya', 'M');
insert into teens(name, gender) VALUES ('Anton', 'M');
insert into teens(name, gender) VALUES ('Ksusha', 'F');
insert into teens(name, gender) VALUES ('Masha', 'F');
insert into teens(name, gender) VALUES ('Polina', 'F');

select t1.name, t1.gender, t2.name, t2.gender from teens t1 cross join teens t2
where t1.gender < t2.gender;
