package com.meetup.entities;

import lombok.Data;

/**
 * Class representing feedback.
 */
@Data
public class Feedback {

    /**
     * Rate of meetup.
     */
    private int rate;
    /**
     * Commentary for meetup.
     */
    private String feedback;
}
