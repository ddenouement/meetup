package com.meetup.entities;

import lombok.Data;

/**.
 * represents a Language
 */
@Data
public class Language {
    /** ID in the database. **/
    private int id;
    /** Name of the language, e.g. "English". **/
    private String name;
}
