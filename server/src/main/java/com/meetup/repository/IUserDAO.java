package com.meetup.repository;

import com.meetup.entities.Language;
import com.meetup.utils.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.ComplaintDTO;
import com.meetup.entities.dto.SimpleUserDTO;
import com.meetup.entities.dto.UserRegistrationDTO;

import java.util.List;

/**.
 * Interface for user repository (UserDAO)
 */
public interface IUserDAO {
    /**
     * .
     *
     * @param login String
     * @return boolean
     */
    boolean isLoginUsed(String login);

    /**
     * .
     *
     * @param email String
     * @return boolean
     */
    boolean isEmailUsed(String email);

    /**
     * .
     *
     * @param log String
     * @return User
     */
    User findUserByLogin(String log);

    /**
     * .
     *
     * @param log String
     * @return User
     */
    User findUserByEmail(String log);

    /**
     * .
     *
     * @param login String
     * @return list of user's roles
     */
    List<Role> findUserRolesByLogin(String login);

    /**
     * .
     *
     * @param us User
     * @param r  String
     */
    void addRoleToUser(User us, String r);

    /**
     * Insert a user and his connections to roles and languages in DB in one
     * request.
     *
     * @param user user to insert
     */
    void insertNewUser(UserRegistrationDTO user);

    /**
     * .
     * get from DB users subscriptions (speakers)
     *
     * @param id int, id of user
     * @return List of users
     */
    List<User> getUsersSubscriptionsToSpeakers(int id);

    /**
     * .
     * get from DB users languages
     *
     * @param id int, id of user
     * @return list of Languages
     */
    List<Language> getUsersLanguages(int id);

    /**
     * .
     * deactivate user in DB
     *
     * @param id int, id of user
     * @return boolean , isSuccessful
     */
    boolean deactivateUser(int id);

    /**
     * .
     *
     * @return List of all not read complaints
     */
    List<ComplaintDTO> getAllNotReadComplaints();

    /**
     * .
     *
     * @param compl ComplaintDTO
     */
    ComplaintDTO postComplaintOn(ComplaintDTO compl);

    /**
     * for admin to mark complaint as read.
     *
     * @param id id of complaint
     * @return bool whether  was successfull
     */
    boolean markAsReadComplaint(int id);

    /**
     * for users to subscribe on speakers.
     *
     * @param userId    who is subscriber
     * @param speakerId on whom user subscribes
     */
    void subscribeToSpeaker(int userId, int speakerId);

    /**
     * users can unsubscribe from speakers.
     *
     * @param userId    who is subscriber
     * @param speakerId on whom user was subscribed
     */
    void unSubscribeFromSpeaker(int userId, int speakerId);

    /**
     * get users-subscribers on a speaker (by his id).
     *
     * @param spekerId int, id of speaker
     * @return List of users
     */
    List<User> getSubscribersOfSpeaker(int spekerId);

    /**
     * @param userId id of user e want to find.
     * @return User found
     */
    User findUserById(int userId);

    /**
     * Get list of simplified users, subscribed on given speaker.
     * @param speakerId id of speaker
     * @return List of SimpleUserDTOs
     */
    List<SimpleUserDTO> getSimpleSubscribersOfSpeaker(int speakerId);

    /**
     * Get all users with role: Speaker.
     * @return
     * List of speakers.
     */
    List<User> getAllSpeakers();

    /**
     * Get all users.
     * @return
     * List of users.
     */
    List<User> getAllUsers();

    /**
     * Change user's password.
     * @param userId id of user to change password for
     * @param newPassword the password to change to
     */
    void changePassword(Integer userId, String newPassword);
}

