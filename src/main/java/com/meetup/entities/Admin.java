package com.meetup.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Admin extends User {

    public Admin() {
        super(new String[]{"ADMIN"});
    }
}
