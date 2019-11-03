package com.meetup.entities.dto;

import com.meetup.entities.Language;
import java.util.List;
import lombok.Data;

/**
 * Profile DTO class.
 */
@Data
public class ProfileDTO {

    /**
     * User login.
     */
    private String login;
    /**
     * User first name.
     */
    private String firstName;
    /**
     * User last name.
     */
    private String lastName;
    /**
     * User about.
     */
    private String about;
    /**
     * Languages of user.
     */
    private List<Language> languages;
}
