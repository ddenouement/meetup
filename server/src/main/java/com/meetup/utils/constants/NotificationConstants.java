package com.meetup.utils.constants;

import java.time.format.DateTimeFormatter;

public final class NotificationConstants {

    /**
     * How many minutes before meetup to notify speaker.
     */
    public static final int MINUTES_BEFORE_HOSTED_MEETUP = 15;
    /**
     * How many minutes before meetup to notify listener.
     */
    public static final int MINUTES_BEFORE_JOINED_MEETUP = 15;

    /**
     * A placeholder for text in a link.
     */
    public static final String TEXT_PLACEHOLDER = ":name";
    /**
     * A placeholder for ID in a link.
     */
    public static final String ID_PLACEHOLDER = ":id";
    /**
     * A placeholder for URL in a link.
     */
    public static final String ROUTER_LINK_PLACEHOLDER = ":router_link";
    /**
     * A link with placeholders.
     */
    // "Your meetup <a href='/#/meetup-profile/17'>Meetup2</a> starts in 15 minutes."
    public static final String LINK =
        "<a href='/#" + ROUTER_LINK_PLACEHOLDER + "/" + ID_PLACEHOLDER
            + "'>" + TEXT_PLACEHOLDER + "</a>";
    /**
     * A placeholder for meetup title.
     */
    public static final String MEETUP_TITLE_PLACEHOLDER = ":meetup_title";
    /**
     * Router link for meetup.
     */
    public static final String MEETUP_ROUTER_LINK = "/meetup-profile";
    /**
     * Router link for leaving feedback.
     */
    public static final String FEEDBACK_ROUTER_LINK = "/feedback";
    /**
     * Link text for leaving feedback.
     */
    public static final String FEEDBACK_LINK_TEXT = "here";


    /**
     * Message for notification of type MEETUP_BOOKED.
     */
    public static final String MEETUP_BOOKED_MESSAGE =
        "Your meetup " + LINK + " is fully booked.";
    /**
     * Message for notification of type HOSTED_MEETUP_STARTS_SOON.
     */
    public static final String HOSTED_MEETUP_STARTS_SOON_MESSAGE =
        "Your meetup " + LINK + " starts in " + MINUTES_BEFORE_HOSTED_MEETUP
            + " minutes.";
    /**
     * Message for notification of type JOINED_MEETUP_STARTS_SOON.
     */
    public static final String JOINED_MEETUP_STARTS_SOON_MESSAGE =
        "Meetup " + LINK + " starts in " + MINUTES_BEFORE_JOINED_MEETUP
            + " minutes.";
    /**
     * Message for notification of type MEETUP_INFO_CHANGED.
     */
    public static final String MEETUP_INFO_CHANGED_MESSAGE_START =
        "Meetup " + LINK + " has changed:\n";
    /**
     * Message for notification of type NEW_SUBSCRIBED_MEETUP.
     */
    public static final String NEW_SUBSCRIBED_MEETUP_MESSAGE =
        "New meetup " + LINK + " from your subscription.";
    /**
     * Message for notification of type LEAVE_FEEDBACK.
     */
    public static final String LEAVE_FEEDBACK_MESSAGE =
        "Please leave your feedback for meetup " + MEETUP_TITLE_PLACEHOLDER
            + " " + LINK + ".";
    /**
     * Message in notification for cancelled meetup.
     */
    public static final String MEETUP_CANCELLED_MESSAGE = "Meetup is cancelled.";
    /**
     * Message in notification for terminated meetup.
     */
    public static final String MEETUP_TERMINATED_MESSAGE = "Meetup is terminated.";
    /**
     * Message in notification for rescheduled meetup.
     */
    public static final String MEETUP_RESCHEDULED_MESSAGE = "Meetup is rescheduled.";
    /**
     * Start of message for notification about meetup start changed.
     */
    public static final String MEETUP_NEW_START_TIME_MESSAGE_START = "New start time is ";

    /**
     * Formatter for meetup start time.
     */
    public static final DateTimeFormatter MEETUP_START_TIME_FORMATTER = DateTimeFormatter
        .ofPattern("dd MMM uuuu, HH:mm");

    // Forbidden to call.
    private NotificationConstants() {
        throw new AssertionError();
    }
}
