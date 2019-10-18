package com.meetup.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**.
 * represents a Language
 */
@Data
@NoArgsConstructor
public class Language {
    /** ID in the database. **/
    private int id;
    /** Name of the language, e.g. "English". **/
    private String name;
}
