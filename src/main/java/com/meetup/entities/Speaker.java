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


    public int getSpeaker_id(){return this.speaker_id;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public String getLogin(){return this.login;}
    public String getAbout(){return this.about;}
    public String getName(){return this.name;}
    public String getSurname(){return this.surname;}
    public String getNativeLanguage(){return  this.nativeLanguage;}
    public void setLogin(String a) {this.login=a;}
    public void setEmail(String a) {this.email=a;}
    public void setPassword(String a) {this.password=a;}
    public void setId(int a) {this.speaker_id=a;}
    public void setName(String a) {this.name=a;}
    public void setSurname(String a) {this.surname=a;}
    public void setNativeLanguage(String a) {this.nativeLanguage=a;}
    public void setAbout(String a) {this.about=a;}
}