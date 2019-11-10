package com.meetup.repository.impl;

import com.meetup.entities.Language;
import com.meetup.entities.User;
import com.meetup.entities.dto.*;
import com.meetup.model.mapper.*;
import com.meetup.repository.IUserDAO;
import com.meetup.utils.DbQueryConstants;
import com.meetup.utils.Role;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * . implementation of DAO User
 */
@Repository
@PropertySource("classpath:sql/user_queries.properties")
public class UserDaoImpl implements IUserDAO {

    /**
     * . NamedParameterJdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * . sql query find_role_id_by_name
     */
    @Value("${find_role_id}")
    private String findRoleIdByName;
    /**
     * . sql query add_role_to_user
     */
    @Value("${add_role_to_user}")
    private String addRoleToUser;
    /**
     * . sql query find user by login
     */
    @Value("${find_user_with_login}")
    private String findByLogin;
    /**
     * sql query to find user by his ID
     */
    @Value("${find_user_with_id}")
    private String findById;
    /**
     * . sql query find user by email
     */
    @Value("${find_user_with_email}")
    private String findUserByEmail;
    /**
     * . sql query find_user_names_by_login
     */
    @Value("${find_user_roles}")
    private String findUserRolesByLogin;
    /**
     * SQL query to insert new user and his/her connections to the roles and
     * languages in one request to the DB.
     */
    @Value("${insert_full_user}")
    private String insertFullUser;
    /**
     * SQL query to upgrade listener to speaker.
     */
    @Value("${upgrade_to_speaker}")
    private String upgradeToSpeaker;
    /**
     * . sql query get from DB subscriptions of user (by his id)
     */
    @Value("${find_subscriptions_by_user_id}")
    private String findSubscriptionOfUserById;
    /**
     * . sql for finding languages of a user
     */
    @Value("${find_languages_by_user_id}")
    private String findUsersLanguages;
    /**
     * .
     * <p>
     * sql query to deactivate user
     */
    @Value("${deactivate_by_user_id}")
    private String deactivateUser;
    /**
     * SQL query to activate user.
     */
    @Value("${activate_by_user_id}")
    private String activateUser;
    /**
     * sql for finding all not read complaints from DB.
     */
    @Value("${get_all_complaints_not_read}")
    private String getAllNotReadComplaints;
    /**
     * sql query to post new complaint.
     */
    @Value("${post_complaint}")
    private String postComplaint;
    /**
     * .
     * <p>
     * sql query to mark complain as read
     */
    @Value("${mark_as_read}")
    private String markComplaint;
    /**
     * sql query to subscribe to speaker.
     */
    @Value("${subscribe_to_speaker}")
    private String subscribeToSpeaker;
    /**
     * sql query to unsubscribe from speaker.
     */
    @Value("${unsubscribe_from_speaker}")
    private String unsubscribeFromSpeaker;
    /**
     * sql query to find users-subscribers of speaker by his ID.
     */
    @Value("${find_subscribers_of_speaker_by_his_id}")
    private String findSubscribersOfSpeaker;
    /**
     * sql query to find simplified users-subscribers of speaker by his ID.
     */
    @Value("${simple_subscribers_of_speaker_by_his_id}")
    private String simpleSubscribersOfSpeaker;
    /**
     * SQL reference script. Get all speakers.
     */
    @Value("${find_all_speakers}")
    private String findAllSpeakers;
    /**
     * SQL reference script. Get all active users.
     */
    @Value("${find_all_users}")
    private String findAllActiveUsers;

    /**
     * SQL reference script. Get all users.
     */
    @Value("${find_users}")
    private String findAllUsers;

    /**
     * SQL reference script. Get all users with complaints count.
     */
    @Value("${find_users_with_complaints_count}")
    private String findAllUsersWithComplaintsCount;
    /**
     * SQL reference script. Get all users with complaints count.
     */
    @Value("${find_users_count}")
    private String findAllUsersCount;

    /**
     * SQL reference script. Change password for specific user.
     */
    @Value("${change_password}")
    private String changePassword;

    /**
     * SQL reference script. Update user.
     */
    @Value("${update_user}")
    private String updateUser;

    /**
     * SQL reference script. Update user rate.
     */
    @Value("${update_user_rate}")
    private String updateUserRate;

    /**
     * SQL reference script. Update user profile.
     */
    @Value("${update_user_profile}")
    private String updateUserProfile;

    /**
     * SQL reference script. Remove user languages.
     */
    @Value("${remove_user_languages}")
    private String removeUserLanguages;

    /**
     * SQL reference script. Add user language.
     */
    @Value("${insert_user_language}")
    private String addUserLanguage;


    /**
     * . .
     *
     * @param login String
     * @return bool
     */
    @Override
    public boolean isLoginUsed(final String login) {
        return findUserByLogin(login) != null;
    }

    /**
     * .
     *
     * @param email String
     * @return bool
     */
    @Override
    public boolean isEmailUsed(final String email) {
        return findUserByEmail(email) != null;
    }

    /**
     * . mapper to User
     *
     * @param resultSet ResultSet
     * @return User (our model)
     * @throws SQLException exception
     */
    private User toPerson(final ResultSet resultSet) throws SQLException {
        User person = new User();
        person.setId(resultSet.getInt(DbQueryConstants.id.name()));
        String login = resultSet.getString(DbQueryConstants.login.name());
        person.setLogin(login);
        person.setFirstName(
            resultSet.getString(DbQueryConstants.first_name.name()));
        person.setLastName(
            resultSet.getString(DbQueryConstants.last_name.name()));
        person.setAbout(resultSet.getString(DbQueryConstants.about.name()));
        person.setEmail(resultSet.getString(DbQueryConstants.email.name()));
        person
            .setPassword(resultSet.getString(DbQueryConstants.password.name()));
        person.setActive(resultSet.getBoolean(DbQueryConstants.active.name()));
        person.setRate(resultSet.getFloat(DbQueryConstants.rate.name()));
        person.setNumRates(resultSet.getInt(DbQueryConstants.num_rates.name()));
        for (Role role : findUserRolesByLogin(login)) {
            person.addRole(role);
        }
        return person;
    }

    /**
     * Get role id by its name.
     *
     * @param roleName role's name in DB
     * @return role's id in DB
     */
    private Integer getRoleId(final String roleName) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
            DbQueryConstants.text.name(), roleName);
        return template.queryForObject(findRoleIdByName, namedParameters,
            Integer.class);
    }

    /**
     * .
     *
     * @param us User
     * @param r String
     */
    @Override
    public void addRoleToUser(final User us, final String r) {
        Map namedParameters = new HashMap();
        namedParameters.put(DbQueryConstants.usId.name(), us.getId());
        namedParameters.put(DbQueryConstants.roleId.name(), getRoleId(r));
        template.update(addRoleToUser, namedParameters);
    }

    /**
     * Create sql Array type from a List of type T.
     *
     * @param elements list of Ts
     * @param typename sql typename for Array
     * @param <T> type of elements in incoming list
     * @return sql Array
     */
    private <T> Array createSqlArray(final List<T> elements,
        final String typename) {
        return template.getJdbcOperations()
            .execute((ConnectionCallback<Array>) con -> con
                .createArrayOf(typename, elements.toArray()));
    }

    /**
     * Insert a user and his connections to roles and languages in DB in one
     * request.
     *
     * @param user user to insert
     */
    @Override
    public void insertNewUser(final UserRegistrationDTO user) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.login.name(), user.getLogin())
            .addValue(DbQueryConstants.email.name(), user.getEmail())
            .addValue(DbQueryConstants.password.name(), user.getPassword())
            .addValue(DbQueryConstants.first_name.name(), user.getFirstName())
            .addValue(DbQueryConstants.last_name.name(), user.getLastName())
            .addValue(DbQueryConstants.about.name(), user.getAbout())
            .addValue(DbQueryConstants.roles.name(),
                createSqlArray(user.getRoles(), "TEXT"))
            .addValue(DbQueryConstants.language_ids.name(),
                createSqlArray(user.getLanguageIds(), "INTEGER"));
        template
            .execute(insertFullUser, param, PreparedStatement::executeQuery);
    }

    /**
     * Upgrade listener to speaker.
     *
     * @param user additional info for upgraded user
     * @param userId of listener to upgrade
     */
    @Override
    public void upgradeToSpeaker(final UserRegistrationDTO user,
        final Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), userId)
            .addValue(DbQueryConstants.login.name(), user.getLogin())
            .addValue(DbQueryConstants.email.name(), user.getEmail())
            .addValue(DbQueryConstants.first_name.name(), user.getFirstName())
            .addValue(DbQueryConstants.last_name.name(), user.getLastName())
            .addValue(DbQueryConstants.about.name(), user.getAbout())
            .addValue(DbQueryConstants.language_ids.name(),
                createSqlArray(user.getLanguageIds(), "INTEGER"));
        template
            .execute(upgradeToSpeaker, param, PreparedStatement::executeQuery);
    }

    /**
     * .
     *
     * @param log String
     * @return User
     */
    @Override
    public User findUserByLogin(final String log) {

        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.login_param.name(), log);
        List<User> foundusers =
            template.query(findByLogin, param,
                (resultSet, i) -> toPerson(resultSet));
        if (foundusers.size() == 0) {
            return null;
        } else {
            return foundusers.get(0);
        }

    }

    /**
     * .
     *
     * @param em String
     * @return User
     */
    @Override
    public User findUserByEmail(final String em) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.email.name(), em);
        List<User> foundUsers =
            template.query(findUserByEmail, param,
                (resultSet, i) -> toPerson(resultSet));
        if (foundUsers.size() == 0) {
            return null;
        } else {
            return foundUsers.get(0);
        }
    }

    /**
     * Find users roles.
     *
     * @param login String
     * @return List <String>
     */
    @Override
    public List<Role> findUserRolesByLogin(final String login) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.login.name(), login);
        ResultSet rs = null;
        return
            template.query(findUserRolesByLogin, param,
                (resultSet, i) -> toRole(resultSet));
    }

    /**
     * mapper to Role entity.
     *
     * @param resultSet ResultSet
     * @return String
     * @throws SQLException exc
     */
    private Role toRole(final ResultSet resultSet) throws SQLException {
        return Role.valueOf(resultSet.getString(DbQueryConstants.name.name()));
    }

    /**
     * . Get Speakers that User is subscribed to
     *
     * @param id int, id of user
     */
    @Override
    public List<User> getUsersSubscriptionsToSpeakers(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.user_id_param.name(), id);
        List<User> subscriptedTo =
            template.query(
                findSubscriptionOfUserById, param,
                (resultSet, i) -> toPerson(resultSet)
            );
        return subscriptedTo;
    }

    /**
     * . get Users languages by his ID
     *
     * @param id int, id of user
     * @return List<Language>
     */
    @Override
    public List<Language> getUsersLanguages(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.user_id_param.name(), id);
        ResultSet rs = null;
        List<Language> languages =
            template.query(findUsersLanguages, param, new LanguageMapper());
        return languages;
    }

    /**
     * . deactivate user in DB
     *
     * @param id int, id of user
     * @return boolean , isSuccessful
     */
    @Override
    public boolean deactivateUser(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.user_id_param.name(), id);
        ResultSet rs = null;
        template.update(deactivateUser, param);
        return true;
    }

    /**
     * Activate user in DB.
     *
     * @param id int, id of user
     * @return boolean, isSuccessful
     */
    @Override
    public boolean activateUser(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.user_id_param.name(), id);
        ResultSet rs = null;
        template.update(activateUser, param);
        return true;
    }

    /**
     * @return List<ComplaintDTO> list of all complaints in DB
     */
    @Override
    public List<ComplaintDTO> getAllNotReadComplaints() {
        return template.query(getAllNotReadComplaints, new ComplaintMapper());
    }

    /**
     * @param compl ComplaintDTO from frontend that we want to insert to DB
     * @return ComplaintDTO with id from DB
     */
    @Override
    public ComplaintDTO postComplaintOn(final ComplaintDTO compl) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.reason.name(), compl.getContent())
            .addValue(DbQueryConstants.id_destination.name(),
                compl.getId_user_to())
            .addValue(DbQueryConstants.time_posted.name(),
                compl.getPostedDate())
            .addValue(DbQueryConstants.id_source.name(),
                compl.getId_user_from());
        ResultSet rs = null;
        template.update(postComplaint, param, holder, new String[]{"id"});
        if (holder.getKeys() != null) {
            compl.setId(holder.getKey().intValue());
        }
        return compl;
    }

    /**
     * Mark complaint as read by its id.
     *
     * @param id id of complaint
     * @return true
     */
    @Override
    public boolean markAsReadComplaint(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id);
        template.update(markComplaint, param);
        return true;
    }

    /**
     * User can subscribe tp speaker.
     *
     * @param userId who is subscriber
     * @param speakerId on whom user subscribes
     */
    @Override
    public void subscribeToSpeaker(final int userId, final int speakerId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userId)
            .addValue(DbQueryConstants.id_speak.name(), speakerId);
        template.update(subscribeToSpeaker, param);
    }

    /**
     * User can unsubscribe from speaker.
     *
     * @param userId who is subscriber
     * @param speakerId on whom user was subscribed
     */
    @Override
    public void unSubscribeFromSpeaker(final int userId, final int speakerId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userId)
            .addValue(DbQueryConstants.id_speak.name(), speakerId);
        template.update(unsubscribeFromSpeaker, param);
    }

    /**
     * Find all subscribers of a given speaker (by his ID).
     *
     * @param speakerId int, id of speaker
     * @return List of users-subscribers
     */
    @Override
    public List<User> getSubscribersOfSpeaker(final int speakerId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.speaker_id_param.name(), speakerId);
        return
            template.query(findById, param,
                (resultSet, i) -> toPerson(resultSet));
    }

    @Override
    public User findUserById(final int userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_param.name(), userId);
        List<User> foundUsers =
            template.query(findById, param,
                (resultSet, i) -> toPerson(resultSet));
        if (foundUsers.size() == 0) {
            return null;
        } else {
            return foundUsers.get(0);
        }
    }

    /**
     * Get list of simplified users, subscribed on given speaker.
     *
     * @param speakerId id of speaker
     * @return List<SimpleUserDTO>
     */
    @Override
    public List<SimpleUserDTO> getSimpleSubscribersOfSpeaker(
        final int speakerId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.speaker_id_param.name(), speakerId);
        return
            template.query(simpleSubscribersOfSpeaker, param,
                new SimpleUserDTOMapper());
    }

    /**
     * Get all users with role: Speaker.
     *
     * @return List of speakers.
     */
    @Override
    public List<User> getAllSpeakers() {
        return template.query(findAllSpeakers, new UserMapper());
    }

    /**
     * Get all active users.
     *
     * @return List of users.
     */
    @Override
    public List<User> getAllActiveUsers() {
        return template.query(findAllActiveUsers, new UserMapper());
    }

    /**
     * Get all users.
     *
     * @return List of users.
     */
    @Override
    public List<User> getAllUsers() {
        return template.query(findAllUsers, new UserMapper());
    }
    /**
     * Get all users with number of complaints for them.
     *
     * @return List of users.
     */
    @Override
    public List<UserComplaintsDTO> getAllUsersWithComplaintsCount(final int limit, final int offset) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue(DbQueryConstants.limit.name(), limit)
                .addValue(DbQueryConstants.offset.name(), offset);
        return template.query(findAllUsersWithComplaintsCount,param, new UserComplaintsDtoMapper());
    }
    /**
     * Count the number of users in database.
     *
     * @return int number of all users
     */
    @Override
    public int getAllUsersCount() {
        List<Integer> res = this.template.query(findAllUsersCount, new IntegerMapper());
        if (res.isEmpty()) {
            return 0;
        } else {
            return res.get(0);
        }
    }
    /**
     * Change user's password.
     *
     * @param userId id of user to change password for
     * @param newPassword the password to change to
     */
    @Override
    public void changePassword(final Integer userId, final String newPassword) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.password.name(), newPassword)
            .addValue(DbQueryConstants.id.name(), userId);
        template.update(changePassword, param);
    }

    /**
     * Update user rate.
     *
     * @param user User.
     */
    @Override
    public void updateRate(final User user) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), user.getId())
            .addValue(DbQueryConstants.rate.name(), user.getRate())
            .addValue(DbQueryConstants.num_rates.name(), user.getNumRates());
        template.update(updateUserRate, param);
    }

    /**
     * Update general info about user.
     *
     * @param user User to be edited.
     * @param profileDTO Edited info.
     */
    @Override
    public void updateInfo(final User user, final ProfileDTO profileDTO) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(),
                user.getId())
            .addValue(DbQueryConstants.first_name.name(),
                profileDTO.getFirstName())
            .addValue(DbQueryConstants.last_name.name(),
                profileDTO.getLastName())
            .addValue(DbQueryConstants.about.name(),
                profileDTO.getAbout());
        template.update(updateUserProfile, param);
    }

    /**
     * Remove user languages.
     *
     * @param userID User ID.
     */
    @Override
    public void removeUserLanguages(final int userID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userID);
        template.update(removeUserLanguages, param);
    }

    /**
     * Add user language.
     *
     * @param languageID Language ID.
     */
    @Override
    public void addUserLanguage(final int userID, final int languageID) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id_user.name(), userID)
            .addValue(DbQueryConstants.id_language.name(),languageID);
        template.update(addUserLanguage, param);
    }
}

