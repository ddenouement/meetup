package com.meetup.service.impl;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.LoginValidatorService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginValidatorServiceImpl implements LoginValidatorService {

    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Autowired
    UserDaoImpl userDao;

    @Override
    public String extractLogin(String token) {
        String userLogin = jwtTokenProvider.getUsername(token);
        if (userLogin == null) {
            throw new NullPointerException();
        } else if (userExists(userLogin)) {
            return userLogin;
        } else {
            throw new NoSuchElementException();
        }
    }

    private boolean userExists(String login) {
        return userDao.findUserByLogin(login) != null;
    }
}
