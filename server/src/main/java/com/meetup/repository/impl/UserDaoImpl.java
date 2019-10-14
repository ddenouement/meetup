package com.meetup.repository.impl;

import com.meetup.entities.Language;
import com.meetup.entities.User;
import com.meetup.model.mapper.LanguageMapper;
import com.meetup.model.mapper.MeetupMapper;
import com.meetup.repository.IUserDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * .
 * implementation of DAO User
 */
@Repository
@PropertySource("classpath:sql/user_queries.properties")
public class UserDaoImpl implements IUserDAO {
    /**
     * .
     * NamedParameterJdbcTemplate
     */
    @Autowired
    private NamedParameterJdbcTemplate template;
    /**
     * .
     * sql query find_role_id_by_name
     */
    @Value("${find_role_id}")
    private String findRoleIdByName;
    /**
     * .
     * sql query add_role_to_user
     */
    @Value("${add_role_to_user}")
    private String addRoleToUser;
    /**
     * .
     * sql query find user by login
     */
    @Value("${find_user_with_login}")
    private String findByLogin;
    /**
     * .
     * sql query find user by email
     */
    @Value("${find_user_with_email}")
    private String findUserByEmail;
    /**
     * .
     * sql query find_user_names_by_login
     */
    @Value("${find_user_roles}")
    private String findUserRolesByLogin;
    /**
     * .
     * sql query add new user to DB
     */
    @Value("${insert_new_user}")
    private String insertNewUser;
    /**
     * .
     * sql query get from DB subscriptions of user (by his id)
     */
    @Value("${find_subscriptions_by_user_id}")
    private String findSubscriptionOfUserById;

    @Value("${find_languages_by_user_id}")
    private String findUsersLanguages;

    /**
     * .
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
     * .
     * mapper to User
     *
     * @param resultSet ResultSet
     * @return User (our model)
     * @throws SQLException exception
     */
    private User toPerson(final ResultSet resultSet) throws SQLException {
        User person = new User();
        person.setId(resultSet.getInt("id"));
        String l = resultSet.getString("login");
        person.setLogin(l);
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setAbout(resultSet.getString("about"));
        person.setEmail(resultSet.getString("email"));
        person.setPassword(resultSet.getString("password"));
        person.setActive(resultSet.getBoolean("active"));
        person.setRate(resultSet.getFloat("rate"));
        for (String a : findUserRolesByLogin(l)) {
            person.addRole(a);
        }
        //TODO: do we need to init languages, subscriptions etc here???????????
        return person;
    }

    /**
     * .
     *
     * @param us User
     * @param r  String
     */
    @Override
    public void addRoleToUser(final User us, final String r) {
        SqlParameterSource namedParameters = new MapSqlParameterSource(
                "text", r);
        Integer roleid = template
                .queryForObject(findRoleIdByName, namedParameters,
                        Integer.class);
        Map namedParameters2 = new HashMap();
        namedParameters2.put("usId", us.getId());
        namedParameters2.put("roleId", roleid);
        template.update(addRoleToUser, namedParameters2);

    }

    /**
     * .
     * add new User to DB
     *
     * @param a User
     */
    @Override
    public void insertNewUser(User a) {
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("login", a.getLogin())
                    .addValue("email", a.getEmail())
                    .addValue("pass", a.getPassword())
                    .addValue("firstName", a.getFirstName())
                    .addValue("lastName", a.getLastName())
                    .addValue("rate", a.getRate())
                    .addValue("active", a.isActive())
                    .addValue("about", a.getAbout());
            template.update(insertNewUser, param, holder, new String[]{"id"});

            if (holder.getKeys() != null) {
                a.setId(holder.getKey().intValue());
                //adding roles to DB
                for (String str : a.getRoles()) {
                    addRoleToUser(a, str);
                }
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
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
        List<User> found_users =
                template.query(findByLogin, param, (resultSet, i) -> {
                    return toPerson(resultSet);
                });
        if (found_users.size() == 0) {
            return null;
        } else {
            return found_users.get(0);
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
                template.query(findUserByEmail, param, (resultSet, i) -> {
                    return toPerson(resultSet);
                });
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
    public List<String> findUserRolesByLogin(final String login) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("login", login);
        ResultSet rs = null;
        return
                template.query(findUserRolesByLogin, param, (resultSet, i) -> {
                    return toRole(resultSet);
                });
    }

    /**
     * .
     *
     * @param resultSet ResultSet
     * @return String
     * @throws SQLException exc
     */
    private String toRole(final ResultSet resultSet) throws SQLException {
        return resultSet.getString("name");
    }

    @Override
    public List<User> getUsersSubscriptionsToSpeakers(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id_param", id);
        ResultSet rs = null;
        List<User> subscriptedTo =
                template.query(findSubscriptionOfUserById, param, (resultSet, i) -> {
                    return toPerson(resultSet);
                });

        return subscriptedTo;
    }

    @Override
    public List<Language> getUsersLanguages(final int id) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_id_param", id);
        ResultSet rs = null;
        List<Language> languages =
                template.query(findUsersLanguages, param, new LanguageMapper());
        return languages;
    }
}
