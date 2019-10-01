package com.meetup.repository;


import com.meetup.entities.Listener;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ImplListenerDAO implements IListenerDAO {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;

    public void setDataSource(DataSource d) {
        this.dataSource = d;
    }


    @Override
    public void insertListener(Listener emp) {
        final String sql = "insert into users(userLogin, userEmail , userPassword) values(?,?,?)";

        final String sql2 = "insert into listeners(userLogin) values(?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getLogin());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.executeUpdate();
            ps.close();
            //to users&&listeners insert.
            PreparedStatement ps2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, emp.getLogin());
            ps2.executeUpdate();
            try (ResultSet generatedKeys = ps2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emp.setId(generatedKeys.getInt(1));
                    ps2.close();
                }
                else {
                    ps2.close();
                    throw new SQLException("Creating listener failed, no ID obtained.");
                }
            }

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
    public List<Listener> getAllListeners() {
        String query = "select listeners.userLogin, userEmail, userPassword, listenerId from listeners inner join users on listeners.userLogin=users.userLogin";

        List<Listener> empList = new ArrayList<Listener>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Listener e = new Listener();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setId(rs.getInt(4));
                empList.add(e);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
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


}