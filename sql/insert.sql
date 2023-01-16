insert into state(state) values ('ready');
insert into state(state) values ('in processing');

insert into category(category) values ('help');
insert into category(category) values ('info');

insert into roles(role) values ('admin');
insert into roles(role) values ('user');

insert into rules(rule) values ('read');
insert into rules(rule) values ('edit');
insert into rules(rule) values ('add');

insert into roles_rules(role_id, rule_id) values (1, 2);
insert into roles_rules(role_id, rule_id) values (1, 3);
insert into roles_rules(role_id, rule_id) values (2, 1);

insert into users(username, role_id) values ('Aleks', 1);
insert into users(username, role_id) values ('Nikolay', 2);
insert into users(username, role_id) values ('Timofey', 2);

insert into item(itemName, user_id, category_id, state_id) values ('Fix internet', 3, 1, 2);
insert into item(itemName, user_id, category_id, state_id) values ('Info about connection', 3, 2, 2);

insert into comments(comment, item_id) values ('Internet do not work from 10 am', 1);
insert into comments(comment, item_id) values ('I want to know my tariff', 4);

insert into attach(file, item_id) values ('screen.png', 1);
insert into attach(file, item_id) values ('smile.jpeg', 4);