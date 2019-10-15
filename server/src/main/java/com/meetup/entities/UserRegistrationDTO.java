package com.meetup.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to transfer data about user on registration.
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserRegistrationDTO {
    /** Login. */
    private String login;
    /** Email. */
    private String email;
    /** Password. */
    private String password;
    /** First name. */
    private String firstName;
    /** Last name. */
    private String lastName;
    /** About. */
    private String about;
    /** User's native languages. */
    private List<String> languages;
    /** User's roles. */
    private List<String> roles;

    /** Init with empty lists. */
    public UserRegistrationDTO() {
        this.languages = new ArrayList<>();
        this.roles = new ArrayList<>();
    }
}
