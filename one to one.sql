create table student (
    id serial primary key,
    name varchar(255)
);

create table record_book (
    id serial primary key,
    university varchar(255),
    course int
);

create table student_record_book (
    id serial primary key,
    student_id int references student(id) unique,
    record_book_id int references record_book(id) unique 
)