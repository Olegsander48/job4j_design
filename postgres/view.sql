create view show_active_orders
as
select s.name as student, a.name as author, b.name, o.active
from students as s
         join orders o on s.id = o.student_id
         join books b on o.book_id = b.id
         join authors a on b.author_id = a.id
where o.active = true
order by a.name desc;

select * from show_active_orders;