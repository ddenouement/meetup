package com.meetup.error;

/**
 * TopicIsUsedException class.
 * Thrown in case if user tries to add topic with name,
 * that already exists.
 */
public class TopicIsUsedException extends ElementIsUsedException {

    /**
     * Constructor.
     */
    public TopicIsUsedException() {
        super();
    }
}
