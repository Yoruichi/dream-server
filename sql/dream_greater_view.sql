CREATE VIEW `dream_greater_view` AS
select dg.`id` greater_id,dg.message_id,dg.`dreamer_id`
,dg.`create_time` greater_create_time,dg.stats greater_stats
,d.nick_name,d.avatar_url,d.stats dreamer_stats
from dream_greater dg left join dreamer d on dg.dreamer_id = d.id