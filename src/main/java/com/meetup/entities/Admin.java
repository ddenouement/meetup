package com.meetup.entities;

public class Admin {
    private String login;
    private String email;
    private String password;

    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public String getLogin(){return this.login;}
    public void setLogin(String a) {this.login=a;}
    public void setEmail(String a) {this.email=a;}
    public void setPassword(String a) {this.password=a;}
}
