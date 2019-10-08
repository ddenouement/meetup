package com.meetup.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class User {
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

    //TODO transfer roles through frontend
    public User(String[] roles) {
        this.roles = Arrays.asList(roles);
    }

    public User(String email, String login, String password) {
        this.email = email;
        this.login = login;
        this.password = password;
        this.roles = new ArrayList<String>();
    }

    public User() {
       this.roles = new ArrayList<String>();
    }
    public void addRole(String role){
        this.roles.add(role);
    }

    public User(String email, String login, String password, String name, String lastName, List<String> roles) {
        this.email = email;
        this.login = login;
        this.firstName = name;
        this.lastName = lastName;
        this.password = password;
        this.roles = roles;
    }
}