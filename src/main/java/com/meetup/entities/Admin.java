package com.meetup.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Admin {
    private String login;
    private String email;
    private String password;
}
