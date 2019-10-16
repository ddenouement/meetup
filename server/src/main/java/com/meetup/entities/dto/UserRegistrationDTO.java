package com.meetup.entities.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * Class to transfer data about user on registration.
 */
@Data
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
    private List<Integer> languageIds;
    /** User's roles. */
    private List<String> roles;

    /** Init with empty lists. */
    public UserRegistrationDTO() {
        this.languageIds = new ArrayList<>();
        this.roles = new ArrayList<>();
    }
}
