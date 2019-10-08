package com.meetup.repository.impl;

import com.meetup.entities.User;
import com.meetup.repository.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource("classpath:sql/user_queries.properties")
public class UserDaoImpl implements IUserDAO {
    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${find_role_id}")
    private String FIND_ROLE_ID_BY_NAME;
    @Value("${add_role_to_user}")
    private String ADD_ROLE_TO_USER;

    @Value("${find_user_with_login}")
    private String FIND_BY_LOGIN;

    @Value("${find_user_with_email}")
    private String FIND_BY_EMAIL;
    @Value("${find_user_roles}")
    private String FIND_USER_ROLES_BY_LOGIN;
    @Value("${insert_new_user}")
    private String INSERT_NEW_USER;

    @Override
    public boolean isLoginUsed(String login) {
        if (findUserByLogin(login)!=null) return true;
        return false;
    }

    @Override
    public boolean isEmailUsed(String email) {
        if (findUserByEmail(email)!=null) return true;
        return false;
    }
    private User toPerson(ResultSet resultSet) throws SQLException {
        User person = new User();
        person.setId(resultSet.getInt("id"));
        String l = resultSet.getString("login");
        person.setLogin(l);
        person.setFirstName(resultSet.getString("first_name"));
        person.setLastName(resultSet.getString("last_name"));
        person.setAbout(resultSet.getString("about"));
        person.setEmail(resultSet.getString("email"));
        person.setPassword(resultSet.getString("password")  );
        person.setActive(resultSet.getBoolean("active"));
        person.setRate(resultSet.getFloat("rate"));
       for( String a : findUserRolesByLogin(l))
           person.addRole(a);
        return person;
    }
    @Override
    public void addRoleToUser(User us, String r){
        SqlParameterSource namedParameters = new MapSqlParameterSource("text", r);
        Integer role_id = template.queryForObject (FIND_ROLE_ID_BY_NAME, namedParameters, Integer.class);

        Map namedParameters2 = new HashMap();
        namedParameters2.put("usId", us.getId());
        namedParameters2.put("roleId", role_id);
        template.update(ADD_ROLE_TO_USER, namedParameters2);

    }

    @Override
    public void insertNewUser(User a) {
         try{

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
        template.update(INSERT_NEW_USER, param,  holder, new String[]{"id"});

        if (holder.getKeys() != null) {
            a.setId(  holder.getKey().intValue());
            //adding roles to DB
            for (String str: a.roles){
                addRoleToUser(a,str);
            }
        } else {
            throw new SQLException("Creating user failed, no ID obtained.");
        }
    }
         catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public User findUserByLogin(String log) {

            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("login", log);
            ResultSet rs = null;
        List<User> found_users =
                template.query(FIND_BY_LOGIN, param,  (resultSet, i) -> {
                return toPerson(resultSet);
            });
        if(found_users.size()==0) return null;
        else return found_users.get(0);

    }
    @Override
    public User findUserByEmail(String em) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("email", em);
        ResultSet rs = null;
        List<User> found_users =
                template.query(FIND_BY_EMAIL, param, (resultSet, i) -> {
                    return toPerson(resultSet);
                });
        if(found_users.size()==0) return null;
        else return found_users.get(0);
    }

    @Override
    public List<String> findUserRolesByLogin(String login) {
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("login", login);
        ResultSet rs = null;
        return
                template.query(FIND_USER_ROLES_BY_LOGIN, param, (resultSet, i) -> {
            //        System.out.println("+");
                    return toRole(resultSet);
                });
    }

    private String toRole(ResultSet resultSet) throws SQLException {
     //   System.out.println(resultSet.getString("name"));
        return resultSet.getString("name");
    }
}
