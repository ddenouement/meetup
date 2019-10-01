package com.meetup.entities;

public class Listener {
    private String login;
    private String email;
    private String password;
    private int listener_id;

    public int getListener_id(){return this.listener_id;}
    public String getPassword(){return this.password;}
    public String getEmail(){return this.email;}
    public String getLogin(){return this.login;}
    public void setLogin(String a) {this.login=a;}
    public void setEmail(String a) {this.email=a;}
    public void setPassword(String a) {this.password=a;}
    public void setId(int a) {this.listener_id=a;}
}
