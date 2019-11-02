package com.meetup.entities.dto;

import com.meetup.utils.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * . UserDTO to transfer data abt User
 */
@Getter
@Setter
@ToString
public class UserShortDTO {

    /**
     * . roles of user
     */
    private List<Role> roles;
    /**
     * . id (from DB)
     */
    private int id;
    /**
     * . login
     */
    private String login;
    /**
     * . email
     */
    private String email;
    /**
     * . first name
     */
    private String firstName;
    /**
     * . last name
     */
    private String lastName;
    /**
     * . is user active
     */

    /**
     * . Constructor
     *
     * @param mroles String[]
     */
    public UserShortDTO(final Role[] mroles) {
        this.roles = Arrays.asList(mroles);
    }


    /**
     * . Constructor
     *
     * @param memail String
     * @param mlogin String
     */
    public UserShortDTO(final String memail, final String mlogin) {
        this.email = memail;
        this.login = mlogin;
        this.roles = new ArrayList<>();
    }

    /**
     * . constructor()
     */
    public UserShortDTO() {
        this.roles = new ArrayList<>();
    }

    /**
     * . constructor
     *
     * @param memail String
     * @param mlogin String
     * @param mname String
     * @param mlastName String
     * @param mroles List<String>
     */
    public UserShortDTO(final String memail, final String mlogin,
                   final String mname, final String mlastName,
                   final List<Role> mroles) {
        this.email = memail;
        this.login = mlogin;
        this.firstName = mname;
        this.lastName = mlastName;
        this.roles = mroles;
    }

    /**
     * . add role to user
     *
     * @param role String
     */
    public void addRole(final Role role) {
        this.roles.add(role);
    }
}

