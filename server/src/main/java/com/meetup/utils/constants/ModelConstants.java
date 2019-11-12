package com.meetup.utils.constants;


/**
 * Utility (constants) class.
 */
public enum ModelConstants {
    /**
     * Key for joined meetups of user which happen in the future.
     */
    joinedMeetupsFuture,
    /**
     * Key for joined meetups of user which have already passed.
     */
    joinedMeetupsPast,
    /**
     * Key for hosted meetups of user which happen in the future.
     */
    hostedMeetupsFuture,
    /**
     * Key for hosted meetups of user which have already passed.
     */
    hostedMeetupsPast,
    /**
     * Key for UserDTO.
     */
    userDTO,

    /**
     * Key for a list of user's subscriptions.
     */
    subscribedTo,
    /**
     * Key for a list of user's badges.
     */
    badges,
    /**
     * Key for a list of meetups.
     */
    meetups,
    /**
     * Key for the number of meetups in DB that satisfy the request.
     */
    meetupCount,
    /**
     * Key for a list of users.
     */
    users,
    /**
     * Key for the number of users in DB that satisfy the request.
     */
    usersCount,
    /**
     * Key for the boolean if user joined meetup.
     */
    ifJoinedMeetup,
    /**
     * Key for a meetup.
     */
    meetup,
    /**
     * Key for the number of articles in DB that satisfy the request.
     */
    articlesCount,
    /**
     * Key for a list of articles.
     */
    articles;
}
