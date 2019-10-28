package com.meetup.utils;

/**
 * Utility (constants) class.
 */
public enum ModelConstants {
    /** Key for joined meetups of user which happen in the future. */
     JOINED_MEETUPS_FUTURE ("joinedMeetupsFuture"),
    /** Key for joined meetups of user which have already passed. */
    JOINED_MEETUPS_PAST("joinedMeetupsPast"),
    /** Key for hosted meetups of user which happen in the future. */
    HOSTED_MEETUPS_FUTURE("hostedMeetupsFuture"),
    /** Key for hosted meetups of user which have already passed. */
    HOSTED_MEETUPS_PAST("hostedMeetupsPast"),
    /** Key for UserDTO. */
    USERDTO("UserDTO"),
    /** Key for a list of user's subscriptions. */
    SUBSCRIPTIONS("subscribedTo"),
    /** Key for a list of user's badges. */
    BADGES("badges");

    private final String name;
    private ModelConstants(String s) {
        name = s;
    }
}
