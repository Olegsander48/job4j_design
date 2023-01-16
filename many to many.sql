create table student (
    id serial primary key,
    name varchar(255)
);

create table university (
    id serial primary key,
    name varchar(255)
);

create table student_university (
    id serial primary key,
    student_id int references student(id),
    university_id int references university(id)
)