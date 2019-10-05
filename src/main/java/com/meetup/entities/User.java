package com.meetup.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class User {
    private String login;
    private String email;
    private String password;
    public List<String> roles;

    public User(String[] roles) {
        this.roles = Arrays.asList(roles);
    }

    public User(String email, String login, String password, List<String> roles) {
        this.email = email;
        this.login = login;
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