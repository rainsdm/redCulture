create view spots_visit_num as
select s.spot_id as spot_id, s.spot_name as spot_name, count(*) as visit_num
from spots s
join records r on s.spot_id = r.spot_id
group by s.spot_id
order by visit_num desc;