--Вывести список всех машин и все привязанные к ним детали. Нужно учесть, что каких-то деталей машина может
-- и не содержать. В таком случае значение может быть null при выводе (например, название двигателя null);
select c.name, cb.name, ce.name, ct.name
from cars c
         left join car_bodies cb on c.body_id = cb.id
         left join car_engines ce on c.engine_id = ce.id
         left join car_transmissions ct on c.transmission_id = ct.id;

/* Вывести кузова, которые не используются НИ в одной машине. "Не используются" значит, что среди записей
таблицы cars отсутствует внешние ключи, ссылающие на таблицу car_bodies. Например, Вы добавили
в car_bodies "седан", "хэтчбек" и "пикап", а при добавлении в таблицу cars указали только внешние ключи
на записи "седан" и "хэтчбек". Запрос, касающийся этого пункта, должен вывести "пикап", т.к. среди машин
нет тех, что обладают таким кузовом; */
select cb.name, cb.id
from cars c
         right join car_bodies cb on c.body_id = cb.id
where c.name is null;

--Вывести двигатели, которые не используются НИ в одной машине
select ce.name, ce.id
from cars c
         right join car_engines ce on c.engine_id = ce.id
where c.name is null;

--Вывести коробки передач, которые не используются НИ в одной машине
select ct.name, ct.id
from cars c
         right join car_transmissions ct on c.transmission_id = ct.id
where c.name is null;