create table departments (
    id serial primary key,
    name varchar(30)
);

create table employees (
    id serial primary key,
    name varchar(30),
    departments_id int references departments(id)
);

insert into departments(name) values ('продажи');
insert into departments(name) values ('реклама');
insert into departments(name) values ('финансы');
insert into departments(name) values ('разработка');
insert into departments(name) values ('ремонт');

insert into employees(name, departments_id) values ('Aleksey', 1);
insert into employees(name, departments_id) values ('Aleksandr', 2);
insert into employees(name, departments_id) values ('Nikolay', 3);
insert into employees(name, departments_id) values ('Vladimir', null );
insert into employees(name, departments_id) values ('Ignat', null );

select * from employees
left join departments
    on employees.id = departments.id;

select * from employees
right join departments
    on employees.id = departments.id;

select * from employees
cross join departments;



select d.name from departments as d
left join employees
    on employees.id = d.id
    where employees.departments_id is null;

select * from departments
left join employees
    on employees.id = departments.id
    where employees.name is not null;

select * from departments
right join employees
    on employees.id = departments.id
    where departments.name is not null;



create table teens (
    id serial primary key , 
    name varchar(30),
    gender varchar(10)
);



insert into teens(name, gender) values ('Aleksey', 'male');
insert into teens(name, gender) values ('Nikolay', 'male');
insert into teens(name, gender) values ('Vladimir', 'male');
insert into teens(name, gender) values ('Natasha', 'female');
insert into teens(name, gender) values ('Vika', 'female');
insert into teens(name, gender) values ('Alena', 'female');

select teens1.name, teens1.gender, teens2.name, teens2.gender
from teens as teens1
cross join teens as teens2
where teens1.gender != teens2.gender;