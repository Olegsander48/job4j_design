insert into rules(rule) values('read');
insert into rules(rule) values('write');

insert into roles(role) values ('user');
insert into roles(role) values ('admin');

insert into roles_rules(role_id, rule_id) values (1, 1);
insert into roles_rules(role_id, rule_id) values (2, 2);

insert into users(name, email, roles_rules_id) values ('Pavel', 'paveul@gmail.com', 1);
insert into users(name, email, roles_rules_id) values ('Valentin', 'gameof@gmail.com', 2);

insert into states(state) values('ready');
insert into states(state) values('process');
insert into states(state) values('send');

insert into categories(category) values('replace');
insert into categories(category) values('remove');
insert into categories(category) values('add');
insert into categories(category) values('fix');

insert into items(name, date, user_id, state_id, category_id)
values ('Fix printer', '2019-10-24', 1, 1, 4);
insert into items(name, date, user_id, state_id, category_id)
values ('Buy new server', '2019-10-31', 2, 2, 3);

insert into comments(message, item_id) VALUES ('We need to fix out printer at 2 floor, room 432', 1);
insert into comments(message, item_id) VALUES ('Our servers out of memory, we need new one for emails', 2);


insert into attachs(path, fileName, item_id) VALUES ('c:\\Documents', 'ticket.pdf', 1);
insert into attachs(path, fileName, item_id) VALUES ('c:\\oders\Documents', 'specifications.doc', 2);