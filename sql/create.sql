create table state (
    id serial primary key,
    state varchar(255) 
);

create table category (
    id serial primary key,
    category varchar(255) 
);

create table roles (
    id serial primary key,
    role varchar(255) 
);

create table rules (
    id serial primary key,
    rule varchar(255) 
);

create table roles_rules (
    id serial primary key,
    role_id int references roles(id),
    rule_id int references rules(id)
);

create table users (
    id serial primary key,
    username varchar(255),
    role_id int references roles(id)
);

create table item (
    id serial primary key,
    itemName varchar(255),
    user_id int references users(id),
    category_id int references category(id),
    state_id int references state(id)
);

create table comments (
    id serial primary key,
    comment varchar(255),
    item_id int references item(id)
);

create table attach (
    id serial primary key,
    file varchar(255),
    item_id int references item(id)
);

