CREATE VIEW `message_view` AS 
select dm.`dreamer_id`,dm.`content`,dm.`type`,dm.`image_url`
,dm.`create_time` message_create_time,dm.stats message_stats
,d.nick_name,d.avatar_url,d.gender,d.create_time dreamer_create_time,d.stats dreamer_stats,d.gender
,count(dg.id) greater_count
,group_concat(dg.dreamer_id)
from dream_message dm left join dreamer d on dm.dreamer_id = d.id left join dream_greater dg on dm.id=dg.message_id
group by dm.id