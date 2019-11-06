package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.*;

/**
 * .
 * class representing filter
 */
@Data
public class Filter {
    private int id;
    private int id_user;
    /**
     *
     * name given by user.
     */
    private String name;
    private int id_language;
    private float rate_from;
    private float rate_to;
    private Date time_from;
    private Date time_to;
    private int topic_id;

    /**
     * used to transfer data to frontend in user-friendly way (with topic name).
     */
    private String topic_name;

    private String title_substring;

    public Filter() {
        // topics_ids = new ArrayList<>()   ;
        //topics = new ArrayList<>();
    }

    public Integer null_or_idLanguage() {
if(id_language==0) return null;
else return id_language;
    }

    public Integer null_or_idTopic() {
        if(topic_id==0) return null;
        else return topic_id;
    }
}
