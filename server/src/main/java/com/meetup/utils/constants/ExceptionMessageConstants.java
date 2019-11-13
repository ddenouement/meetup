package com.meetup.utils.constants;

/**
 * Utility message constants class.
 */
public final class ExceptionMessageConstants {

    /**
     * Private utility class constructor.
     */
    private ExceptionMessageConstants() {
        throw new AssertionError();
    }

    public static final String MEETUP_NOT_FOUND = "Meetup not found!";

    public static final String TOPIC_NOT_FOUND = "Topic not found!";

    public static final String ARTICLE_NOT_FOUND = "Article not found!";

    public static final String USER_NOT_FOUND = "User not found!";

    public static final String EMAIL_NOT_FOUND = "User with this email doesn't exist!";

    public static final String OUT_OF_SLOTS = "No available slots!";

    public static final String EMAIL_IS_USED = "E-mail is already in use!";

    public static final String LOGIN_IS_USED = "Login is already in use!";

    public static final String TOPIC_IS_USED = "Topic is in use, delete is forbidden!";

    public static final String LANGUAGE_IS_USED = "Language is in use, delete is forbidden!";

    public static final String INCORRECT_LOGIN_CREDENTIALS = "Invalid username or password!";

    public static final String INCORRECT_SCRIPT = "Incorrect SQL script!";

    public static final String BADGE_NAME_IS_USED = "Badge with such name already exists!";

    public static final String ILLEGAL_MEETUP_STATE = "Meetup operation with this state is prohibited!";

}
