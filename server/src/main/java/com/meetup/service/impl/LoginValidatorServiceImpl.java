package com.meetup.service.impl;

import com.meetup.controller.jwtsecurity.JwtTokenProvider;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.ILoginValidatorService;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**.
 * Login validator service (implementation). Used to manage validity of user
 * login.
 */
@Service
public class LoginValidatorServiceImpl implements ILoginValidatorService {

    /**.
     * JSON web token provider.
     */
    private JwtTokenProvider jwtTokenProvider;
    /**.
     * User repository.
     */
    private IUserDAO userDao;

    /**.
     * Login validator service constructor.
     *
     * @param jwtTokenProvider JSON web token provider
     * @param userDao User repository
     */
    LoginValidatorServiceImpl(
        @Autowired final JwtTokenProvider jwtTokenProvider,
        @Autowired final UserDaoImpl userDao) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDao = userDao;
    }

    /**.
     * Extracting login from JSON web token.
     *
     * @param token Token, that used to extract login from
     * @return String representation of user login
     */
    @Override
    public String extractLogin(final String token) {
        String userLogin = jwtTokenProvider.getUsername(token);
        if (userLogin == null) {
            throw new NullPointerException();
        } else if (userExists(userLogin)) {
            return userLogin;
        } else {
            throw new NoSuchElementException();
        }
    }
    /**.
     * Extracting id from JSON web token.
     *
     * @param token Token, that used to extract id from
     * @return Int representation of user id
     */
    @Override
    public int extractId(final String token) {
       Integer userId = jwtTokenProvider.getUserId(token);
        if (userId == null) {
            throw new NullPointerException();
        }
        return userId;
    }
    /**.
     * Check if user exists in database.
     *
     * @param login User login
     * @return Boolean value (exists or not)
     */
    private boolean userExists(final String login) {
        return userDao.findUserByLogin(login) != null;
    }
}
