package com.meetup.entities.dto;

import lombok.Data;

/**
 * Class used to transfer data about user, where we dont need all his fields.
 * For example, as for getting a list of subscribers.
 */
@Data
public class SimpleUserDTO {
    /**
     * id.
     */
    private int id;
    /** login.
     */
    private String login;
    /**.
     * first name
     */
    private String firstName;
    /**.
     * last name
     */
    private String lastName;
    /**.
     * rate of user
     */
    private Float rate;
    /**
     * Number of individual ratings.
     */
    private Integer numRates;
}
