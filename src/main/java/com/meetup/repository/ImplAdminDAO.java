package com.meetup.repository;

import com.meetup.entities.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public class ImplAdminDAO implements IAdminDAO {
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
    public void insertAdmin(Admin emp) {
        final String sql = "insert into users(userLogin, userEmail , userPassword) values(?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
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
        String query = "select userLogin, userEmail, userPassword from users";

        List<Admin> empList = new ArrayList<Admin>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Admin e = new Admin();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
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