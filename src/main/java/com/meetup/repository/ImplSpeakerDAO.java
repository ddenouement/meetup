package com.meetup.repository;
import com.meetup.entities.Speaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ImplSpeakerDAO implements ISpeakerDAO {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;


    @Override
    public List<Speaker> getAllSpeakers() {
        String query = "select speakers.userLogin, userEmail, userPassword, speakerid, speakerabout, speakername, speakersurname speakernativelanguage from speakers inner join users on speakers.userLogin=users.userLogin";
        List<Speaker> empList = new ArrayList<Speaker>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                Speaker e = new Speaker();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setId(rs.getInt(3));
                e.setAbout(rs.getString(4));
                e.setName(rs.getString(5));
                e.setSurname(rs.getString(6));;
                e.setNativeLanguage(rs.getString(7));

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

    @Override
    public void insertSpeaker(Speaker emp) {
        final String sql = "insert into users(userLogin, userEmail , userPassword) values(?,?,?)";

        final String sql2 = "insert into speakers(userLogin, speakername, speakersurname, speakerNativeLanguage, speakerabout) values(?,?,?,?,?)";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, emp.getLogin());
            ps.setString(2, emp.getEmail());
            ps.setString(3, emp.getPassword());
            ps.executeUpdate();
            ps.close();
            //to users&&speakers insert.
            PreparedStatement ps2 = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
            ps2.setString(1, emp.getLogin());
            ps2.setString(2, emp.getName());
            ps2.setString(3, emp.getSurname());
            ps2.setString(4, emp.getNativeLanguage());
            ps2.setString(5,emp.getAbout());
            ps2.executeUpdate();
            try (ResultSet generatedKeys = ps2.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    emp.setId(generatedKeys.getInt(1));
                    ps2.close();
                }
                else {
                    ps2.close();
                    throw new SQLException("Creating speaker failed, no ID obtained.");
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
}
