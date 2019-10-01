package com.meetup.entities;

public class Listener {
    private String login;
    private String email;
    private String password;
    private int listener_id;

    public int getListener_id() {
        return listener_id;
    }

    public void setListener_id(int listener_id) {
        this.listener_id = listener_id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

