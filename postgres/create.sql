create table rules(
    id serial primary key,
    rule varchar(255)
);

create table roles (
    id    serial primary key,
    role  varchar(255)
);

create table roles_rules(
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users (
    id    serial primary key,
    name  varchar(255),
    email text,
    roles_rules_id int references roles_rules(id)
);

create table states(
    id serial primary key,
    state varchar(255)
);

create table categories(
    id serial primary key,
    category varchar(255)
);

create table items(
    id serial primary key,
    name varchar(255),
    date date,
    user_id int references users(id),
    state_id int references states(id),
    category_id int references categories(id)
);

create table comments(
    id serial primary key,
    message varchar(255),
    item_id int references items(id)
);

create table attachs(
    id serial primary key,
    path varchar(255),
    fileName varchar(255),
    item_id int references items(id)
);

