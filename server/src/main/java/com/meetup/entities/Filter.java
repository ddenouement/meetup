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
    /**
     * user to whom this filter is saved
     */
    private int id_user;
    /**
     * name given by user.
     */
    private String name;
    private int id_language;
    private float rate_from;
    private float rate_to;
    private Date time_from;
    private Date time_to;
    private int topic_id;
    private String title_substring;

    /**
     * used to transfer data to frontend in user-friendly way (with topic name).
     */
    private String topic_name;


    public String nullOrTitleSubstring() {
        if (title_substring.equals("")) return null;
        else return title_substring;
    }

    public Integer nullOrIdLanguage() {
        if (id_language == 0) return null;
        else return id_language;
    }

    /**.
     * Not to add rate range as a clause to sql string, if range is default
     * @return boolean whether the rate range is default (0-5)
     */
    private boolean isDefaultRateRange() {
        return this.getRate_from() == 0.0 && this.getRate_to() == 5.0;
    }


    public Integer nullOrIdTopic() {
        if (topic_id == 0) return null;
        else return topic_id;
    }

    public Float nullOrRateTo() {
        if (rate_to == 0) return null;
        if (isDefaultRateRange()) return null;
        else return rate_to;
    }

    public Float nullOrRateFrom() {
        if (rate_from == 0) return null;
        if (isDefaultRateRange()) return null;
        else return rate_from;
    }


}
