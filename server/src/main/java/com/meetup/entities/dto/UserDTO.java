package com.meetup.entities.dto;

import com.meetup.entities.Language;
import com.meetup.entities.Role;
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
public class UserDTO {


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
    private boolean isActive;
    /**
     * . about user
     */
    private String about;
    /**
     * . rate of user
     */
    private Float rate;
    /**
     * . languages
     */
    private List<Language> languages;

    /**
     * . Constructor
     *
     * @param mroles String[]
     */
    public UserDTO(final Role[] mroles) {
        this.roles = Arrays.asList(mroles);
        this.languages = new ArrayList<Language>();

    }


    /**
     * . Constructor
     *
     * @param memail String
     * @param mlogin String
     */
    public UserDTO(final String memail, final String mlogin) {
        this.email = memail;
        this.login = mlogin;
        this.roles = new ArrayList<>();
        this.languages = new ArrayList<Language>();
    }

    /**
     * . constructor()
     */
    public UserDTO() {
        this.roles = new ArrayList<>();
        this.languages = new ArrayList<Language>();
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
    public UserDTO(final String memail, final String mlogin,
        final String mname, final String mlastName,
        final List<Role> mroles) {
        this.email = memail;
        this.login = mlogin;
        this.firstName = mname;
        this.lastName = mlastName;
        this.roles = mroles;
        this.languages = new ArrayList<Language>();
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

