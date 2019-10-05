package com.meetup.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Speaker extends User {
    public Speaker() {
        super(new String[]{"SPEAKER", "LISTENER"});
    }

    private int speaker_id;
    private String about;
    private String nativeLanguage;
    private String name;
    private String surname;

}