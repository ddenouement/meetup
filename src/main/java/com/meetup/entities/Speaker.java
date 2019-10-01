package com.meetup.entities;


public class Speaker {
    private String login;
    private String email;
    private String password;
    private int speaker_id;
    private String about;
    private String nativeLanguage;
    private String name;
    private String surname;


    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAbout() {
        return about;
    }

    public void setSpeaker_id(int speaker_id) {
        this.speaker_id = speaker_id;
    }

    public int getSpeaker_id() {
        return speaker_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getSurname() {
        return surname;
    }
}