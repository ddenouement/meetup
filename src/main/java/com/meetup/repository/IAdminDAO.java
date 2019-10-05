package com.meetup.repository;

import com.meetup.entities.Admin;

import java.util.List;

public interface IAdminDAO {
    List<Admin> getAllAdmins();

    void insertAdmin(Admin emp);

    Admin findAdminByLogin(String login);
}
