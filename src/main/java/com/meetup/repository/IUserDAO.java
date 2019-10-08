package com.meetup.repository;

import com.meetup.entities.User;

import java.util.List;

public interface IUserDAO {
    boolean isLoginUsed(String login);

    boolean isEmailUsed(String email);

    User findUserByLogin(String log);

    User findUserByEmail(String log);

    List<String> findUserRolesByLogin(String login);

    void addRoleToUser(User us, String r);

    void insertNewUser(User a);
}
