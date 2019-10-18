package com.meetup.repository;

import com.meetup.entities.Language;
import com.meetup.entities.Role;
import com.meetup.entities.User;
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
     * @param user user to insert
     */
    void insertNewUser(UserRegistrationDTO user);

    /**
     * .
     * get from DB users subscriptions (speakers)
     *
     * @param id int, id of user
     * @return  List of users
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
}
