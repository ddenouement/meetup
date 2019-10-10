package com.meetup.service;

import com.meetup.repository.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private UserDaoImpl userDAO;

    @Override
    public UserDetails loadUserByUsername(String userlogin) throws UsernameNotFoundException {
        com.meetup.entities.User userInfo = userDAO.findUserByLogin(userlogin);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String r : userInfo.getRoles()) {
            GrantedAuthority a = new SimpleGrantedAuthority(r);
            authorities.add(a);
        }

        //System.out.println("fb"+builder.r().size());
        UserDetails userDetails = (UserDetails) new User(userInfo.getLogin(),
                userInfo.getPassword(), authorities);
        return new User(userInfo.getLogin(),
                userInfo.getPassword(), authorities);


    }
}
