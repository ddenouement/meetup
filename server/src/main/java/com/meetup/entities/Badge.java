package com.meetup.entities;

import lombok.Data;

@Data
public class Badge {

    /**
     * Badge's ID in database.
     */
    private Integer id;
    /**
     * Badge's name, e.g. 'Polyglot'.
     */
    private String name;
    /**
     * An SQL script which returns true if user with specified ID should get
     * this badge.
     */
    private String script;
}
