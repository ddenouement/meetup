package com.meetup.utils.constants;

/**
 * Utility email constants class.
 */
public final class EmailServiceConstants {

    /**
     * Private utility class constructor.
     */
    private EmailServiceConstants(){
        throw new AssertionError();
    }

    public static final String HOST = "smtp.gmail.com";

    public static final String PORT = "587";

    public static final String TLS = "true";

    public static final String AUTH = "true";

    public static final String TOKEN_LOGIN = "TOKEN_LOGIN";

    public static final String TOKEN_PASSWORD = "TOKEN_PASSWORD";

    public static final String FROM_EMAIL = "meetupplus77";

    public static final String FROM_PASS = "Qwerty123_";

    public static final String WELCOME_SUBJECT = "WELCOME TO MEETUP";

    public static final String CHANGE_PASSWORD_SUBJECT = "CHANGE PASSWORD ON MEETUP";

    public static final String REGISTER_BODY = "<h1>Hello my friend</h1>\n" +
        "<p>Thank you for registering at <a href=\"https://app-meetup.herokuapp.com\">MeetUp</a>\n" +
        "<p>Your login credentials:\n" +
        "<p style=\"margin-left: 10px;\"> https://app-meetup.herokuapp.com/login\n" +
        "<p style=\"margin-left: 10px;\"> login: TOKEN_LOGIN\n" +
        "<p>Hugs!\n" +
        "<p>Oleg from MeetUp\n";

    public static final String CHANGE_PASSWORD_BODY = "<h1>Hello my friend</h1>\n" +
        "<p>Your login credentials:\n" +
        "<p style=\"margin-left: 10px;\"> https://app-meetup.herokuapp.com/change-password\n"
        +
        "<p style=\"margin-left: 10px;\"> login: TOKEN_LOGIN\n" +
        "<p style=\"margin-left: 10px;\"> password: TOKEN_PASSWORD\n"
        +
        "<p>Hugs!\n" +
        "<p>Oleg from MeetUp\n";

    public static final String ALPHANUMERIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        + "0123456789"
        + "abcdefghijklmnopqrstuvxyz";

}
