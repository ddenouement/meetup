CREATE OR REPLACE FUNCTION do_filter(
                                    _title_param      TEXT  = NULL
                                    , _id_language_param INTEGER = NULL
                                    , _topic_id_param     BIGINT = NULL
                                    , _start_date_param TIMESTAMP = NULL
                                    , _end_date_param       TIMESTAMP = NULL
                                    , _rate_from   REAL = NULL
                                    , _rate_to       REAL = NULL
)
    RETURNS TABLE(id bigint, id_speaker bigint, id_language int, id_state int, title text, start_time timestamp, duration_minutes smallint,
    min_attendees int, max_attendees int, description text, id_topic bigint) AS
$do$
    BEGIN
    RETURN QUERY EXECUTE concat(
$$SELECT DISTINCT meetups.id, meetups.id_speaker, meetups.id_language, id_state, title, start_time, duration_minutes, min_attendees, max_attendees, description, id_topic
                 FROM meetups
                 LEFT JOIN users on users.id=id_speaker$$
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
)
USING $1, $2, $3, $4, $5, $6, $7;
    END
$do$ LANGUAGE plpgsql;