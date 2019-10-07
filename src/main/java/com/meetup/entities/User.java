package com.meetup.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public   class User {
    private int id;
    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private boolean isActive;
    private String about;
    private double rate;
    private String password;
    public List<String> roles;

    public User(String[] roles) {
        this.roles = Arrays.asList(roles);
    }

    public User(String email, String login, String password, List<String> roles) {
        this.email = email;
        this.login = login; }
    public User(){}

    public User(String email, String login, String password, String name, String lastName, List <String> roles) {
        this.email = email;
        this.login = login;
        this.firstName = name;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}