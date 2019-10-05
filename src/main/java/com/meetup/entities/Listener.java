package com.meetup.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Listener extends User {
    private int listener_id;

    public Listener() {
        super(new String[]{"LISTENER"});
    }

    @Override
    public String toString() {
        return "Listener{" +
                "login='" + this.getLogin() + '\'' +
                ", email='" + this.getEmail() + '\'' +
                ", password='" + this.getPassword() + '\'' +
                ", listener_id=" + listener_id +
                '}';
    }
}

