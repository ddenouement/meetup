package com.meetup.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.Data;

/**.
 * model class of User
 */
@Data
public class User {
    /**.
     * roles of user
     */
    private List<String> roles;
    /**.
     * id (from DB)
     */
    private int id;
    /**.
     * login
     */
    private String login;
    /**.
     * email
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
    /**.
     * about user
     */
    private String about;
    /**.
     * rate of user
     */
    private Float rate;
    /**.
     * password
     */
    private String password;

    /**.
     * Constructor
     * @param roles String[]
     */
    public User(final String[] roles) {
        this.roles = Arrays.asList(roles);
    }

    /**.
     * Constructor
     * @param memail String
     * @param mlogin String
     * @param mpassword String
     */
    public User(final String memail, final String mlogin,
                final String mpassword) {
        this.email = memail;
        this.login = mlogin;
        this.password = mpassword;
        this.roles = new ArrayList<String>();
    }

    /**.
     * constructor()
     */
    public User() {
        this.roles = new ArrayList<String>();
    }

    /**.
     *
     * @param memail String
     * @param mlogin String
     * @param mpassword String
     * @param mname String
     * @param mlastName String
     * @param mroles List<String>
     */
    public User(final String memail, final String mlogin,
                final String mpassword,
                final String mname, final String mlastName,
                final List<String> mroles) {
        this.email = memail;
        this.login = mlogin;
        this.firstName = mname;
        this.lastName = mlastName;
        this.password = mpassword;
        this.roles = mroles;
    }

    /**.
     *
     * @param role String
     */
    public void addRole(final String role) {
        this.roles.add(role);
    }

}
