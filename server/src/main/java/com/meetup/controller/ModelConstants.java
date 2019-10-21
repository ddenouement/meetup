package com.meetup.controller;

public final class ModelConstants {
    private ModelConstants() {
        // closed empty constructor
    }

    /** Key for joined meetups of user which happen in the future. */
    public static final String JOINED_MEETUPS_FUTURE = "joinedMeetupsFuture";
    /** Key for joined meetups of user which have already passed. */
    public static final String JOINED_MEETUPS_PAST = "joinedMeetupsPast";
    /** Key for hosted meetups of user which happen in the future. */
    public static final String HOSTED_MEETUPS_FUTURE = "hostedMeetupsFuture";
    /** Key for hosted meetups of user which have already passed. */
    public static final String HOSTED_MEETUPS_PAST = "hostedMeetupsPast";
    /** Key for UserDTO. */
    public static final String USERDTO = "UserDTO";
    /** Key for a list of user's subscriptions. */
    public static final String SUBSCRIPTIONS = "subscribedTo";
}
