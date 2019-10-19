package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Model class of Topic.
 */
@Data
@NoArgsConstructor
public class Topic {

    /**
     * Id of topic.ts.
     */
    private int id;
    /**
     * Name of topic.ts.
     */
    private String name;
}

