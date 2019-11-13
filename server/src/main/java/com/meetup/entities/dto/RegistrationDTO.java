package com.meetup.entities.dto;
import lombok.Data;

/**
 * Class to transfer data about user on registration.
 */
@Data
public class RegistrationDTO {

    /** Login. */
    private String login;
    /** Email. */
    private String email;

    /** Init with empty lists. */
    public RegistrationDTO() {
    }
}
