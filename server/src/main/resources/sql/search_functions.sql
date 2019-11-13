CREATE OR REPLACE FUNCTION do_filter(
                                    _title_param      TEXT  = NULL
                                    , _id_language_param INTEGER = NULL
                                    , _topic_id_param     BIGINT = NULL
                                    , _start_date_param TIMESTAMP = NULL
                                    , _end_date_param       TIMESTAMP = NULL
                                    , _rate_from   REAL = NULL
                                    , _rate_to       REAL = NULL
)
    RETURNS TABLE(id bigint, id_speaker bigint, id_language int, topic_name text, language_name text, id_state int, title text, start_time timestamp, duration_minutes smallint,
    min_attendees int, max_attendees int, description text, id_topic bigint, speaker_login text, speaker_first_name text, speaker_last_name text, speaker_rate real, speaker_num_rates integer) AS
$do$
    BEGIN
    RETURN QUERY EXECUTE concat(
$$SELECT DISTINCT meetups.id, meetups.id_speaker, meetups.id_language, t.name AS topic_name, l.name AS language_name, id_state, title, start_time, duration_minutes, min_attendees, max_attendees, description, id_topic, u.login AS speaker_login, u.first_name AS speaker_first_name, u.last_name AS speaker_last_name, u.rate AS speaker_rate, u.num_rates AS speaker_num_rates
                 FROM meetups
                 INNER JOIN topics t on meetups.id_topic = t.id
                 INNER JOIN languages l on meetups.id_language = l.id
                 LEFT JOIN users u on u.id=id_speaker$$
, concat_ws('
AND    '
   , '
WHERE  TRUE'
   , CASE WHEN $1 IS NOT NULL THEN 'position($1 in  title)>0' END
   , CASE WHEN $2 IS NOT NULL THEN 'id_language = $2' END
   , CASE WHEN $3 IS NOT NULL THEN 'id_topic = $3' END
   , CASE WHEN $4 IS NOT NULL THEN 'start_time >= $4' END
   , CASE WHEN $5 IS NOT NULL THEN 'start_time <= $5' END
   , CASE WHEN $6 IS NOT NULL THEN 'rate >=  $6' END
   , CASE WHEN $7 IS NOT NULL AND $6 IS NOT NULL THEN 'rate <=  $7' END
)
,
        concat(
             CASE WHEN $6 IS NULL AND $7 IS NOT NULL THEN 'AND (rate <= $7 OR rate IS NULL)' END
    )
,
        concat(
            'AND u.active = TRUE AND
  meetups.id_state IN (select id from meetup_states where name = ''SCHEDULED'' or name = ''BOOKED'')
  ORDER BY start_time ASC'
    )
)
USING $1, $2, $3, $4, $5, $6, $7;
    END
$do$ LANGUAGE plpgsql;