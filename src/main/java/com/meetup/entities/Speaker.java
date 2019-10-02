package com.meetup.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class Speaker {
    private String login;
    private String email;
    private String password;
    private int speaker_id;
    private String about;
    private String nativeLanguage;
    private String name;
    private String surname;

}