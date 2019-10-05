package com.meetup.repository.impl;


import com.meetup.entities.Listener;
import com.meetup.repository.IListenerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
@PropertySource({"classpath:sql/listener_queries.properties", "classpath:sql/user_queries.properties"})
public class ListenerDaoImpl implements IListenerDAO {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource d) {
        this.dataSource = d;
    }

    @Value("${insert_new_listener}")
    private String INSERT_NEW_LISTENER;

    @Value("${get_all_listeners}")
    private String GET_ALL_LISTENERS;

    @Value("${insert_new_user}")
    private String INSERT_NEW_USER;

    @Override
    public void insertListener(Listener emp) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("login", emp.getLogin())
                    .addValue("email", emp.getEmail())
                    .addValue("pass", emp.getPassword());
            template.update(INSERT_NEW_USER, param);

            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource param2 = new MapSqlParameterSource()
                    .addValue("login", emp.getLogin());
            template.update(INSERT_NEW_LISTENER, param2, holder, new String[]{"listenerid"});

            if (holder.getKeys() != null) {
                emp.setListener_id(holder.getKey().intValue());
            } else {
                throw new SQLException("Creating listener failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    @Override
    public List<Listener> getAllListeners() {
        List<Listener> empList = new ArrayList<Listener>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(GET_ALL_LISTENERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Listener e = new Listener();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setListener_id(rs.getInt(4));
                empList.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empList;
    }

    @Override
    public Listener findListenerByLogin(String login) {
        for (Listener l : getAllListeners()) {
            if (l.getLogin().equals(login)) return l;
        }
        return null;
    }







       /* List<Listener> empList = new ArrayList<Listener>();

    template.query(GET_ALL_LISTENERS, (rs, i) -> {

        Listener e = new Listener();
        e.setLogin(rs.getString(1));
        e.setEmail(rs.getString(2));
        e.setPassword(rs.getString(3));
        e.setListener_id(rs.getInt(4));
        return e;

    });


        return empList;
    }*/


}