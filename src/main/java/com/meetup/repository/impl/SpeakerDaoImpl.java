package com.meetup.repository.impl;
import com.meetup.entities.Speaker;
import com.meetup.repository.ISpeakerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
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
@PropertySource({"classpath:sql/speaker_queries.properties","classpath:sql/user_queries.properties"})
public class SpeakerDaoImpl implements ISpeakerDAO {
    @Autowired
    DataSource dataSource;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate template;
    @Value("${insert_new_speaker}")
    private String INSERT_NEW_SPEAKER;

    @Value("${get_all_speakers}")
    private String GET_ALL_SPEAKERS;

    @Value("${insert_new_user}")
    private String INSERT_NEW_USER;

    @Override
    public List<Speaker> getAllSpeakers() {
        List<Speaker> empList = new ArrayList<Speaker>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = dataSource.getConnection();
            ps = con.prepareStatement(GET_ALL_SPEAKERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                Speaker e = new Speaker();
                e.setLogin(rs.getString(1));
                e.setEmail(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setSpeaker_id(rs.getInt(3));
                e.setAbout(rs.getString(4));
                e.setName(rs.getString(5));
                e.setSurname(rs.getString(6));
                ;
                e.setNativeLanguage(rs.getString(7));

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
    public void insertSpeaker(Speaker emp) {
        try {
            SqlParameterSource param = new MapSqlParameterSource()
                    .addValue("login", emp.getLogin())
                    .addValue("email", emp.getEmail())
                    .addValue("pass", emp.getPassword());
            template.update(INSERT_NEW_USER, param);

            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource param2 = new MapSqlParameterSource()
                    .addValue("login", emp.getLogin())
           .addValue("name", emp.getName())
            .addValue("surname", emp.getSurname())
            .addValue("native", emp.getNativeLanguage())
            .addValue("abt",emp.getAbout());
            template.update(INSERT_NEW_SPEAKER, param2, holder, new String[]{"speakerid"});

            if (holder.getKeys() != null) {
                emp.setSpeaker_id(holder.getKey().intValue());
            } else {
                throw new SQLException("Creating speaker failed, no ID obtained.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }
}
