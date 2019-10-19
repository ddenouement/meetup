package com.meetup.repository.impl;

import com.meetup.entities.Language;
import com.meetup.entities.Role;
import com.meetup.entities.User;
import com.meetup.entities.dto.UserRegistrationDTO;
import com.meetup.model.mapper.LanguageMapper;
import com.meetup.repository.IUserDAO;
import java.sql.Array;
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
import org.springframework.stereotype.Repository;

/**
 * . implementation of DAO User
 */
@Repository
@PropertySource("classpath:sql/user_queries.properties")
public class UserDaoImpl implements IUserDAO {

    //TODO Hikari pool
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
     * . sql query add new user to DB
     */
    @Value("${insert_new_user}")
    private String insertNewUser;
    /**
     * SQL query to insert new user and his/her connections to the roles and
     * languages in one request to the DB.
     */
    @Value("${insert_full_user}")
    private String insertFullUser;
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
    /**.
     *
     * sql quoey to deactivate user
     */
    @Value("${deactivate_by_user_id}")
    private String deactivateUser;

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
        person.setId(resultSet.getInt("id"));
        String login = resultSet.getString("login");
        person.setLogin(login);
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setAbout(resultSet.getString("about"));
        person.setEmail(resultSet.getString("email"));
        person.setPassword(resultSet.getString("password"));
        person.setActive(resultSet.getBoolean("active"));
        person.setRate(resultSet.getFloat("rate"));
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
            "text", roleName);
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
        namedParameters.put("usId", us.getId());
        namedParameters.put("roleId", getRoleId(r));
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
            .addValue("login", user.getLogin())
            .addValue("email", user.getEmail())
            .addValue("password", user.getPassword())
            .addValue("first_name", user.getFirstName())
            .addValue("last_name", user.getLastName())
            .addValue("about", user.getAbout())
            .addValue("roles", createSqlArray(user.getRoles(), "TEXT"))
            .addValue("language_ids",
                createSqlArray(user.getLanguageIds(), "INTEGER"));
        template.execute(insertFullUser, param, ps -> ps.executeQuery());
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
            .addValue("login", log);
        ResultSet rs = null;
        List<User> foundusers =
            template.query(findByLogin, param, (resultSet, i) -> {
                return toPerson(resultSet);
            });
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
            .addValue("email", em);
        ResultSet rs = null;
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
     * .
     *
     * @param login String
     * @return List <String>
     */
    @Override
    public List<Role> findUserRolesByLogin(final String login) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("login", login);
        ResultSet rs = null;
        return
            template.query(findUserRolesByLogin, param,
                (resultSet, i) -> toRole(resultSet));
    }

    /**
     * .
     *
     * @param resultSet ResultSet
     * @return String
     * @throws SQLException exc
     */
    private Role toRole(final ResultSet resultSet) throws SQLException {
        return Role.valueOf(resultSet.getString("name"));
    }

    /**
     * . Get Speakers that User is subscribed to
     *
     * @param id int, id of user
     */
    @Override
    public List<User> getUsersSubscriptionsToSpeakers(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue("user_id_param", id);
        ResultSet rs = null;
        List<User> subscriptedTo =
            template.query(
                findSubscriptionOfUserById, param, (resultSet, i) -> {
                    return toPerson(resultSet);
                }
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
            .addValue("user_id_param", id);
        ResultSet rs = null;
        List<Language> languages =
            template.query(findUsersLanguages, param, new LanguageMapper());
        return languages;
    }
    /**
     * .
     * deactivate user in DB
     *
     * @param id int, id of user
     * @return boolean , isSuccessful
     */
    @Override
   public boolean deactivateUser(final int id){
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id_param", id);
        ResultSet rs = null;
        template.update(deactivateUser,param);
        return true;
    }
}

