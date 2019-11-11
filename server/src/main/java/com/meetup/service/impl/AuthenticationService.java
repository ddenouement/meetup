package com.meetup.service.impl;

import com.meetup.utils.Role;
import com.meetup.repository.impl.UserDaoImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**.
 * Implementation of UserDetailsService
 */
@Service
public class AuthenticationService implements UserDetailsService {
    /**.
     * methods to work with DB (in aspect of Users)
     */
    @Autowired
    private UserDaoImpl userDAO;

    /**.
     *
     * @param userlogin String
     * @return UserDetails (spring class)
     * @throws UsernameNotFoundException exc
     */
    @Override
    public UserDetails loadUserByUsername(final String userlogin)
        throws UsernameNotFoundException {
        com.meetup.entities.User userInfo = userDAO.findUserByLogin(userlogin);
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : userInfo.getRoles()) {
            GrantedAuthority a = new SimpleGrantedAuthority(role.name());
            authorities.add(a);
        }

          return new User(userInfo.getLogin(),
            userInfo.getPassword(), authorities);

    }
}
