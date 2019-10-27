package com.meetup.utils;

public enum DbQueryConstants {
    ID_AUTHOR("id_author"),
    ID_TITLE("id_title"),
    CONTENTS("contents"),
    TIME_POSTED("time_posted"),
    ID("id"),
    ID_ARTICLE("id_article"),
    ID_TOPIC("id_topic"),
    NAME("name"),
    SCRIPT("script"),
    USER_ID("user_id"),
    ID_SPEAKER("id_speaker"),
    ID_USER("id_user"),
    ID_LANGUAGE("id_language"),
    ID_STATE("id_state"),
    TITLE("title"),
    START_TIME("start_time"),
    DURATION_MINUTES("duration_minutes"),
    MIN_ATTENDEES("min_attendees"),
    MAX_ATTENDEES("max_attendees"),
    DESCRIPTION("description"),
    ID_MEETUP("id_meetup"),
    TEXT("text"),
    US_ID("usId"),
    ROLE_ID("roleId"),
    LOGIN("login"),
    FIRST_NAME("first_name"),
    LAST_NAME("last_name"),
    ABOUT("about"),
    EMAIL("email"),
    PASSWORD("password"),
    LANGUAGE_IDS("language_ids"),
    ROLES("roles"),
    LOGIN_PARAM("login_param"),
    USER_ID_PARAM("user_id_param"),
    REASON("reason"),
    ID_DESTINATION("id_destination"),
    ID_SOURCE("id_source"),
    ID_SPEAK("id_speak"),
    SPEAKER_ID_PARAM("speaker_id_param"),
    ID_PARAM("id_param"),
    ACTIVE("active"),
    RATE("rate"),
    NUM_RATES("num_rates");
    private final String name;
    private DbQueryConstants(String s) {
        name = s;
    }



}
