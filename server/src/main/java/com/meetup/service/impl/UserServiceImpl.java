package com.meetup.service.impl;

import com.meetup.entities.Feedback;
import com.meetup.entities.Meetup;
import com.meetup.entities.User;
import com.meetup.entities.dto.*;
import com.meetup.error.EmailDoesntExistException;
import com.meetup.error.EmailIsUsedException;
import com.meetup.error.LoginIsUsedException;
import com.meetup.error.UserNotFoundException;
import com.meetup.repository.IMeetupDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.repository.impl.MeetupDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import com.meetup.service.IMeetupService;
import com.meetup.service.INotificationService;
import com.meetup.service.IUserService;
import com.meetup.utils.Role;
import com.meetup.utils.UserDTOConverter;

import java.util.Arrays;
import java.util.List;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * . Class for working with users
 */
@Service
public class UserServiceImpl implements IUserService {

    /**
     * . methods to DB considering users.
     */
    private IUserDAO userDao;
    /**
     * .
     */
    private IMeetupDAO meetupDao;
    /**
     * .
     */
    private IMeetupService meetupService;
    /**
     * Notification operations.
     */
    private INotificationService notificationService;

    /**
     * Constructor.
     *
     * @param userDao             User repository.
     * @param meetupDao           Meetup repository.
     * @param meetupService       Meetup service.
     * @param notificationService Notification service.
     */
    @Autowired
    UserServiceImpl(final UserDaoImpl userDao,
                    final MeetupDaoImpl meetupDao,
                    final MeetupServiceImpl meetupService,
                    final NotificationServiceImpl notificationService) {
        this.userDao = userDao;
        this.meetupDao = meetupDao;
        this.meetupService = meetupService;
        this.notificationService = notificationService;
    }

    /**
     * .
     *
     * @param user User (that has role of listener) to register
     */
    @Override
    public void registerAsListener(
            final UserRegistrationDTO user) {
        if (userDao.isLoginUsed(user.getLogin())) {
            throw new LoginIsUsedException();
        } else if (userDao.isEmailUsed(user.getEmail())) {
            throw new EmailIsUsedException();
        } else {
            user.getRoles().add(Role.LISTENER);
            userDao.insertNewUser(user);
        }
    }

    /**
     * .
     *
     * @param user User (that has role of speaker) to register
     */
    @Override
    public void registerAsSpeaker(
            final UserRegistrationDTO user) {
        if (userDao.isLoginUsed(user.getLogin())) {
            throw new LoginIsUsedException();
        } else if (userDao.isEmailUsed(user.getEmail())) {
            throw new EmailIsUsedException();
        } else {
            user.getRoles().addAll(Arrays.asList(Role.LISTENER, Role.SPEAKER));
            userDao.insertNewUser(user);
        }
    }

    /**
     * Upgrade listener to speaker.
     *
     * @param user   additional info for upgraded user
     * @param userId of listener to upgrade
     */
    @Override
    public void upgradeToSpeaker(final UserRegistrationDTO user,
                                 final Integer userId) {
        User oldUser = userDao.findUserById(userId);
        if (!oldUser.getLogin().equals(user.getLogin()) && userDao
                .isLoginUsed(user.getLogin())) {
            throw new LoginIsUsedException();
        } else if (!oldUser.getEmail().equals(user.getEmail()) && userDao
                .isEmailUsed(user.getEmail())) {
            throw new EmailIsUsedException();
        } else {
            userDao.upgradeToSpeaker(user, userId);
        }
    }

    /**
     * Update general info about user.
     *
     * @param profileDTO User profile
     */
    @Override
    public void updateProfile(final ProfileDTO profileDTO) {
        User user = userDao.findUserByLogin(profileDTO.getLogin());
        userDao.updateInfo(user, profileDTO);
        updateLanguages(user, profileDTO);
    }

    /**
     * Update user languages.
     *
     * @param user       User, to update languages.
     * @param profileDTO Updated profile.
     */
    private void updateLanguages(final User user,
                                 final ProfileDTO profileDTO) {
        userDao.removeUserLanguages(user.getId());
        for (Integer langID : profileDTO.getLanguageIds()) {
            userDao.addUserLanguage(user.getId(), langID);
        }
    }

    /**
     * .
     *
     * @param id user`s id
     * @return UserDTO
     */
    @Override
    public UserDTO getProfileUserDTO(final int id) {
        User us = userDao.findUserById(id);
        if (us == null) {
            throw new UserNotFoundException();
        }
        UserDTO dtouser = UserDTOConverter.convertToUserDTO(us);
        dtouser.setLanguages(userDao.getUsersLanguages(us.getId()));
        return dtouser;
    }


    /**
     * Get all active speakers.
     *
     * @return List<User> of speakers.
     */
    @Override
    public List<User> getAllSpeakers() {
        return userDao.getAllSpeakers();
    }

    /**
     * Get all active users.
     *
     * @return List<User> of users.
     */
    @Override
    public List<User> getAllActiveUsers() {
        return userDao.getAllActiveUsers();
    }

    /**
     * Get all users.
     *
     * @return List<User> of users.
     */
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<UserComplaintsDTO> getAllUsersWithComplaints(final int limit, final int offset) {
        return userDao.getAllUsersWithComplaintsCount(limit, offset);
    }

    /**
     * Count the number of users in database.
     *
     * @return int number of all users
     */
    @Override
    public int getAllUsersCount() {
        return userDao.getAllUsersCount();
    }

    /**
     * .
     *
     * @param id id
     * @return List of speakers, that user is subscripted to
     */
    @Override
    public List<User> getUsersSubscriptionsToSpeakers(final int id) {
        return userDao.getUsersSubscriptionsToSpeakers(id);
    }

    /**
     * Deactivate user. This in turn removes this user from all of his future
     * joined meetups and cancels all of his future hosted meetups.
     *
     * @param id user id
     * @return boolean whether successful operation or not
     */
    @Override
    public boolean deactivateUser(final int id) {
        // Leave future joined meetups.
        for (Meetup meet : meetupService.getJoinedMeetupsFuture(id)) {
            meetupService.leaveMeetup(meet.getId(), id);
        }
        // Cancel future hosted meetups.
        for (Meetup meetup : meetupDao.getSpeakerMeetupsFuture(id)) {
            meetupService.cancelMeetup(meetup.getId(), id);
        }
        userDao.deactivateUser(id);
        notificationService.sendProfileDeactivatedNotification(id);
        return true;
    }

    /**
     * Activate user.
     *
     * @param id user id
     * @return boolean whether successful operation or not
     */
    @Override
    public boolean activateUser(final int id) {
        userDao.activateUser(id);
        notificationService.sendProfileActivatedNotification(id);
        return true;
    }

    /**
     * @return List of complaints from DB.
     */
    @Override
    public List<ComplaintDTO> getAllNotReadComplaints() {
        return userDao.getAllNotReadComplaints();
    }

    /**
     * @param complaintDTO complaint without source
     * @param login        login of source
     * @throws UserNotFoundException if no user with this login
     */
    @Override
    public void postComplaintOn(final ComplaintDTO complaintDTO,
                                final String login) throws UserNotFoundException {
        User u = userDao.findUserByLogin(login);
        if (u == null) {
            throw new UserNotFoundException();
        }
        int idSource = u.getId();
        complaintDTO.setPostedDate(complaintDTO.getPostedDate());
        complaintDTO.setIdUserFrom(idSource);
        userDao.postComplaintOn(complaintDTO);
    }

    /**
     * @param id Complaint id.
     * @return boolean whether is successfull
     */
    @Override
    public boolean markAsReadComplaint(final int id) {
        return userDao.markAsReadComplaint(id);
    }

    /**
     * User can subscribe on speaker.
     *
     * @param userId    who is subscriber
     * @param speakerId on whom user subscribes
     */
    @Override
    public void subscribeToSpeaker(final int userId, final int speakerId) {
        User u = userDao.findUserById(userId);
        if (u == null) {
            throw new UserNotFoundException();
        }
        userDao.subscribeToSpeaker(userId, speakerId);
    }

    /**
     * User can unsubscribe from speaker.
     *
     * @param userId    who is subscriber
     * @param speakerId on whom user subscribes
     */
    @Override
    public void unSubscribeFromSpeaker(final int userId, final int speakerId) {
        User u = userDao.findUserById(userId);
        if (u == null) {
            throw new UserNotFoundException();
        }
        userDao.unSubscribeFromSpeaker(userId, speakerId);
    }

    /**
     * Get list of subscribers of a given speaker (by his ID).
     *
     * @param speakerId speaker
     * @return list of subscribers
     */
    @Override
    public List<User> getSubscribersOfSpeaker(final int speakerId) {
        return userDao.getSubscribersOfSpeaker(speakerId);
    }

    /**
     * Get basic info about users who are subscribed on speaker.
     *
     * @param speakerId speaker
     * @return List of SimpleUserDTO
     */
    @Override
    public List<SimpleUserDTO> getSimpleSubscribersOfSpeaker(
            final int speakerId) {
        return userDao.getSimpleSubscribersOfSpeaker(speakerId);
    }

    /**
     * Change user's password.
     *
     * @param userId      id of user to change password for
     * @param newPassword the password to change to
     */
    @Override
    public void changePassword(final Integer userId, final String newPassword) {
        userDao.changePassword(userId, newPassword);
    }

    /**
     * Rate specific meetup.
     *
     * @param meetupID  Meetup ID.
     * @param userLogin User login
     * @param feedback  Feedback object.
     */
    @Override
    public void rateMeetup(final int meetupID,
                           final String userLogin,
                           final Feedback feedback) {
        User user = userDao.findUserByLogin(userLogin);
        if (user == null) {
            throw new UserNotFoundException();
        }
        meetupDao.rateMeetup(meetupID, user.getId(), feedback);

        Meetup currentMeetup = meetupDao.findMeetupByID(meetupID);
        User speakerOfMeetup = userDao
                .findUserById(currentMeetup.getSpeakerId());
        updateUserRate(speakerOfMeetup, feedback);
    }

    @Override
    public String userPrimaryRole(final int userId) {
        List<Role> roles = userDao.findUserById(userId).getRoles();
        for (Role r : roles) {
            if (r.getAuthority().equals("SPEAKER")) {
                return "SPEAKER";
            }
            if (r.getAuthority().equals("ADMIN")) {
                return "ADMIN";
            }
        }

        return "LISTENER";
    }

    /**
     * Send email to user.
     *
     * @param email User email.
     */
    @Override
    public void sendEmail(String email, String login) {
        String USER_NAME = "meetupplus77";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "Qwerty123_"; // GMail password
        String RECIPIENT = email;
        String from = USER_NAME;
        String pass = PASSWORD;
        String to = RECIPIENT; // list of recipient email addresses
        String subject = "WELCOME TO MEETUP";
        String body = "<h1>Hello my friend</h1>\n" +
                "<p>Thank you for registering at <a href=\"https://app-meetup.herokuapp.com\">MeetUp</a>\n" +
                "<p>Your login credentials:\n" +
                "<p style=\"margin-left: 10px;\"> https://app-meetup.herokuapp.com/login\n" +
                "<p style=\"margin-left: 10px;\"> login: " + login + "\n" +
                "<p>Hugs!\n" +
                "<p>Oleg from MeetUp\n";
        sendFromGMail(from, pass, to, subject, body);
    }

    /**
     * Send email to user.
     *
     * @param email User email.
     */
    @Override
    public void sendNewPassword(String email) {
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            throw  new EmailDoesntExistException();
        }
        int userId = user.getId();
        String userLogin = user.getLogin();
        changePassword(userId,"Qwerty123_");
        String USER_NAME = "meetupplus77";  // GMail user name (just the part before "@gmail.com")
        String PASSWORD = "Qwerty123_"; // GMail password
        String RECIPIENT = email;
        String from = USER_NAME;
        String pass = PASSWORD;
        String to = RECIPIENT; // list of recipient email addresses
        String subject = "CHANGE PASSWORD ON MEETUP";
        String body = "<h1>Hello my friend</h1>\n" +
                "<p>Your login credentials:\n" +
                "<p style=\"margin-left: 10px;\"> https://app-meetup.herokuapp.com/change-password\n" +
                "<p style=\"margin-left: 10px;\"> login: " + userLogin + "\n" +
                //TODO add password generator
                "<p style=\"margin-left: 10px;\"> password: " + "Qwerty123_" + "\n" +
                "<p>Hugs!\n" +
                "<p>Oleg from MeetUp\n";
        sendFromGMail(from, pass, to, subject, body);
    }

    private static void sendFromGMail(String from, String pass, String to,
                                      String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(subject);
            message.setContent(body, "text/html");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }


    /**
     * Update speaker rate after rating meetup.
     *
     * @param user     User.
     * @param feedback Feedback of meetup.
     */
    private void updateUserRate(final User user,
                                final Feedback feedback) {
        float rate =
                (user.getRate() * user.getNumRates() + feedback.getRate())
                        / (user.getNumRates() + 1);
        user.setRate(rate);
        user.setNumRates(user.getNumRates() + 1);
        userDao.updateRate(user);
    }


}
