package com.meetup.utils;

/**
 * Utility (constants) class.
 */

//TODO ENUM
public final class MeetupStateConstants {

    /**
     * Utility closed constructor.
     */
    private MeetupStateConstants() {
        // closed empty constructor
    }

    /**
     * Scheduled meetup state ID.
     */
    public static final int SCHEDULED = 2;

    /**
     * Booked meetup state ID.
     */
    public static final int BOOKED = 3;

    /**
     * Canceled meetup state ID.
     */
    public static final int CANCELED = 4;

    /**
     * In progress meetup state ID.
     */
    public static final int IN_PROGRESS = 5;

    /**
     * Terminated meetup state ID.
     */
    public static final int TERMINATED = 6;

    /**
     * Passed meetup state ID.
     */
    public static final int PASSED = 7;

}
