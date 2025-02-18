insert into engine(horsepowers) values (612);
insert into engine(horsepowers) values (585);
insert into engine(horsepowers) values (510);
insert into engine(horsepowers) values (565);

insert into car(model, engine_id) VALUES ('Mercedes g63', 2);
insert into car(model, engine_id) VALUES ('Mercedes e63', 1);
insert into car(model, engine_id) VALUES ('Mercedes GTS', 3);
insert into car(model, engine_id) VALUES ('Range Rover SVR', 4);
insert into car(model, engine_id) VALUES ('Range Rover SVA', 3);

select c.model as Модель, e.horsepowers as Мощность
from car c join engine e on e.id = c.engine_id;

select c.model as Model, e.horsepowers as Power
from car c join engine e on e.id = c.engine_id;

select c.model модель, e.horsepowers мощность
from car c join engine e on e.id = c.engine_id;