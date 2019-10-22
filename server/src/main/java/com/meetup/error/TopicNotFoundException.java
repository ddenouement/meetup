package com.meetup.error;

/**
 * TopicNotFoundException class.
 * Caused by getting non-existing topic.ts.
 */
public class TopicNotFoundException extends RuntimeException {

    /**
     * Constructor.
     */
    public TopicNotFoundException() {
        super("Topic not found!");
    }
}