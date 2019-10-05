package com.meetup.repository.impl;

import com.meetup.entities.Admin;
import com.meetup.entities.Listener;
import com.meetup.repository.IAdminDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository

@PropertySource("classpath:sql/user_queries.properties")
public class AdminDaoImpl implements IAdminDAO {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource d) {
        this.dataSource = d;
    }

    @Value("${get_all_users}")
    private String GET_ALL_USERS;

    @Value("${insert_new_user}")
    private String INSERT_NEW_USER;

    @Override
    public void insertAdmin(Admin emp) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(INSERT_NEW_USER);
            ps.setString(1, emp.getLogin());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }

    @Override
    public List<Admin> getAllAdmins() {


        List<Admin> empList = new ArrayList<Admin>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(GET_ALL_USERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Admin e = new Admin();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
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
    public Admin findAdminByLogin(String login) {
        for (Admin a : getAllAdmins()) {
            if (a.getLogin().equals(login)) return a;
        }
        return null;
    }

}