package com.meetup.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Getter
@Setter
@ToString
public class UserDTO {


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
private List<Meetup> hosted;
private List<Meetup> joined;
private  List<Language> languages;
private List<User> subscriptedToSpeakers;
private List<Filter> filters;

        /**.
         * Constructor
         * @param mroles String[]
         */
        public UserDTO(final String[] mroles) {
            this.roles = Arrays.asList(mroles);
            this.languages = new ArrayList<Language>();
            this.filters = new ArrayList<Filter>();
            this.joined = new ArrayList<Meetup>();
            this.hosted = new ArrayList<Meetup>();
            this.subscriptedToSpeakers = new ArrayList<User>();
        }


        /**.
         * Constructor
         * @param memail String
         * @param mlogin String
         * @param mpassword String
         */
        public UserDTO(final String memail, final String mlogin,
                    final String mpassword) {
            this.email = memail;
            this.login = mlogin;
            this.password = mpassword;
            this.roles = new ArrayList<String>();
            this.languages = new ArrayList<Language>();
            this.filters = new ArrayList<Filter>();
            this.joined = new ArrayList<Meetup>();
            this.hosted = new ArrayList<Meetup>();
            this.subscriptedToSpeakers = new ArrayList<User>();
        }

        /**.
         * constructor()
         */
        public UserDTO() {
            this.roles = new ArrayList<String>();
            this.languages = new ArrayList<Language>();
            this.filters = new ArrayList<Filter>();
            this.joined = new ArrayList<Meetup>();
            this.hosted = new ArrayList<Meetup>();
            this.subscriptedToSpeakers = new ArrayList<User>();
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
        public UserDTO(final String memail, final String mlogin,
                    final String mpassword,
                    final String mname, final String mlastName,
                    final List<String> mroles) {
            this.email = memail;
            this.login = mlogin;
            this.firstName = mname;
            this.lastName = mlastName;
            this.password = mpassword;
            this.roles = mroles;
            this.languages = new ArrayList<Language>();
            this.filters = new ArrayList<Filter>();
            this.joined = new ArrayList<Meetup>();
            this.hosted = new ArrayList<Meetup>();
            this.subscriptedToSpeakers = new ArrayList<User>();
        }

        /**.
         *
         * @param role String
         */
        public void addRole(final String role) {
            this.roles.add(role);
        }
        public void addSpeakerToSubscriptions(final User u) {
            this.subscriptedToSpeakers.add(u);
        }
        public void addLanguage(final Language lan) {
            this.languages.add(lan);
        }
        public void joinMeeting(final Meetup a) {
            this.joined.add(a);
        }
        public void hostMeeting(final Meetup a) {
            this.hosted.add(a);
        }
        public void addFilter(final Filter f){this.filters.add(f);}
    }

