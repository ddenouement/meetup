package com.meetup.utils;

/**
 * Types of notifications in the system.
 */
public enum NotificationType {
    /**
     * Meetup hosted by user is fully booked.
     */
    MEETUP_BOOKED(1),
    /**
     * Meetup hosted by user starts soon.
     */
    HOSTED_MEETUP_STARTS_SOON(2),
    /**
     * Meetup joined by user starts soon.
     */
    JOINED_MEETUP_STARTS_SOON(3),
    /**
     * Information aboup a meetup joined by user has changed.
     */
    MEETUP_INFO_CHANGED(4),
    /**
     * A new meetup hosted by the speaker user is subscribed to is created.
     */
    NEW_SUBSCRIBED_MEETUP(5),
    /**
     * Meetup joined by user has ended and he is suggested to leave feedback.
     */
    LEAVE_FEEDBACK(6),
    /**
     * User's profile is deactivated by admin.
     */
    PROFILE_DEACTIVATED(7),
    /**
     * User's profile is activated by admin.
     */
    PROFILE_ACTIVATED(8);

    /** Id of notification type in the database. */
    private int id;

    /**
     * Initialize notification type with id.
     * @param id id in the database
     */
    NotificationType(final int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
