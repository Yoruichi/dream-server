CREATE VIEW `dream_reply_view` AS
select dr.`id` reply_id,dr.message_id,dr.`dreamer_id`,dr.`content`,dr.`reply_dreamer_id`
,dr.`create_time` reply_create_time,dr.stats reply_stats
,d.nick_name,d.avatar_url,d.stats dreamer_stats
from dream_reply dr left join dreamer d on dr.dreamer_id = d.id