package com.meetup.entities;

import lombok.Data;

@Data
public class Language {
    /** ID in the database. **/
    private int id;
    /** Name of the language, e.g. "English". **/
    private String name;
}
