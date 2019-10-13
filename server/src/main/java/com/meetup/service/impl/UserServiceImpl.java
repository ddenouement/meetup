package com.meetup.service.impl;

import com.meetup.entities.User;
import com.meetup.entities.UserDTO;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.RoleProcessor;
import com.meetup.service.UserService;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**.
 * Class for working with users
 */
@Component
public class UserServiceImpl implements UserService {

    //TODO enum
    /**.
     * role
     */
    private static final String LISTENER = "LISTENER";
    /**.
     * role
     */
    private static final String SPEAKER = "SPEAKER";
    /**.
     * role
     */
    private static final String ADMIN = "ADMIN";
    /**.
     * methods to DB
     */
    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private MeetupDaoImpl meetupDao;

    /**.
     * @param user User (that has role of listener) to register
     * @return Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsListener(final User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao
            .isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists",
                HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().add(LISTENER);
            userDao.insertNewUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    /**.
     * @param user User (that has role of speaker) to register
     * @return Entity, representing information about register status
     */
    @Override
    public ResponseEntity<String> registerAsSpeaker(User user) {
        if (userDao.isLoginUsed(user.getLogin()) || userDao
            .isEmailUsed(user.getEmail())) {
            return new ResponseEntity<>("User already exists",
                HttpStatus.FORBIDDEN);
        } else {
            user.getRoles().addAll(Arrays.asList(LISTENER, SPEAKER));
            userDao.insertNewUser(user);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        }
    }

    /**.
     *
     * @param user User
     * @return User, but with new profile
     */
    @Override
    public User updateProfile(User user) {
        //TODO implement
        return null;
    }

    /**.
     *
     * @param user User
     * @return User with new password
     */
    @Override
    public User changePassword(User user) {
        //TODO implement
        return null;
    }

    /**.
     *
     * @param login String
     * @return User
     */
    @Override
    public UserDTO getProfile(final String login) {
        User us = userDao.findUserByLogin(login);
        return convertToUserDTO(us);

    }

    private UserDTO convertToUserDTO(User us) {
        UserDTO newUser = new UserDTO();
        newUser.setAbout(us.getAbout());
        newUser.setActive(us.isActive());
        newUser.setEmail(us.getEmail());
        newUser.setId(us.getId());
        newUser.setFirstName(us.getFirstName());
        newUser.setLastName(us.getLastName());
        newUser.setRoles(us.getRoles());
        newUser.setPassword(us.getPassword());
        newUser.setLogin(us.getLogin());
        newUser.setRate(us.getRate());
        if(RoleProcessor.isSpeaker(us)) {
            newUser.setHosted(meetupDao.getSpeakerMeetups(us.getId()));
        }
        newUser.setJoined(meetupDao.getUsersJoinedMeetups(us.getId()));
    //    newUser.s


        return newUser;
    }


    /**.
     *
     * @return List<User> of speakers
     */
    @Override
    public List<User> getAllSpeakers() {
        //TODO implement
        return null;
    }


}
