create table employees (
    id serial primary key,
    name varchar(30)
);

create table departments (
    id serial primary key,
    name varchar(30),
    employees_id int references employees(id)
);

insert into employees(name) values ('Aleksey');
insert into employees(name) values ('Aleksandr');
insert into employees(name) values ('Nikolay');
insert into employees(name) values ('Vladimir');
insert into employees(name) values ('Ignat');

insert into departments(name, employees_id) values ('продажи', 1);
insert into departments(name, employees_id) values ('реклама', 2);
insert into departments(name, employees_id) values ('финансы', 3);
insert into departments(name, employees_id) values ('разработка', null );
insert into departments(name, employees_id) values ('ремонт', null );

select * from employees
left join departments
    on employees.id = departments.id;

select * from employees
right join departments
    on employees.id = departments.id;

select * from employees
cross join departments;



select * from departments
left join employees
    on employees.id = departments.id
    where departments.employees_id is null;

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

select * from teens as teens1 cross join teens as teens2;