package com.meetup.entities.dto;
import lombok.Data;

@Data
public class UserComplaintsDTO {
    /**
     * id.
     */
    private int id;
    /** login.
     */
    private String login;
    /** email.
     */
    private String email;

    /**.
     * first name
     */
    private String firstName;
    /**.
     * last name
     */
    private String lastName;
    /**.
     * is user active
     */
    private boolean isActive;

    /** number of complaints.
     */
    private int complaintsCount;


}
