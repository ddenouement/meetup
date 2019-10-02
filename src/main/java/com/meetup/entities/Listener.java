package com.meetup.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Listener {

    private String login;
    private String email;
    private String password;
    private int listener_id;


    @Override
    public String toString() {
        return "Listener{" +
                "login='" + login + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", listener_id=" + listener_id +
                '}';
    }
}

