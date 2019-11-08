package com.meetup.repository.impl;

import com.meetup.entities.Badge;
import com.meetup.entities.User;
import com.meetup.model.mapper.BadgeMapper;
import com.meetup.model.mapper.StringMapper;
import com.meetup.model.mapper.UserMapper;
import com.meetup.repository.IBadgeDAO;
import com.meetup.utils.DbQueryConstants;
import java.util.List;
import java.util.Objects;
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
 * Implemetation of IBadgeDAO.
 */
@Repository
@PropertySource("classpath:sql/badge_queries.properties")
public class BadgeDaoImpl implements IBadgeDAO {

    /**
     * Provides JDBC operations with named parameters.
     **/
    private NamedParameterJdbcTemplate template;

    /**
     * Initialize with the instance of NamedParameterJdbcTemplate.
     *
     * @param template template to use to perform JDBC operations
     */
    @Autowired
    public BadgeDaoImpl(final NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    /**
     * SQL script to select all rows in table badges.
     */
    @Value("${find_all_badges}")
    private String findAllBadges;

    /**
     * SQL script to select a row with specific id in table badges.
     */
    @Value("${find_badge_by_id}")
    private String findBadgeById;

    /**
     * SQL script to select a row with specific name (case insensitive) in table
     * badges.
     */
    @Value("${find_badge_by_name}")
    private String findBadgeByName;

    /**
     * SQL script to insert a row in table badges.
     */
    @Value("${insert_badge}")
    private String insertBadge;

    /**
     * SQL script to update a row in table badges.
     */
    @Value("${update_badge}")
    private String updateBadge;

    /**
     * SQL script to delete a rows with specified id in table badges.
     */
    @Value("${delete_badge}")
    private String deleteBadge;

    /**
     * SQL script to select all badges that a specified user would get.
     */
    @Value("${get_user_badges}")
    private String getUserBadges;

    /**
     * SQL script to select all users that would get a specified badge.
     */
    @Value("${get_users_with_badge}")
    private String getUsersWithBadge;

    /**
     * Return all badges in database.
     *
     * @return a list of all badges
     */
    @Override
    public List<Badge> findAll() {
        return template.query(findAllBadges, new BadgeMapper());
    }

    /**
     * Return a badge with specified ID in the database.
     *
     * @param id ID of badge to return
     * @return a badge with specified ID
     */
    @Override
    public Badge findById(final Integer id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id);
        List<Badge> badges = template
            .query(findBadgeById, param, new BadgeMapper());
        if (badges.isEmpty()) {
            return null;
        } else {
            return badges.get(0);
        }
    }

    /**
     * Return a badge with specified name in the database (case insensitive).
     *
     * @param name name of badge to return (case insensitive)
     * @return a badge with specified ID
     */
    @Override
    public Badge findByName(final String name) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.name.name(), name);
        List<Badge> badges = template
            .query(findBadgeByName, param, new BadgeMapper());
        if (badges.isEmpty()) {
            return null;
        } else {
            return badges.get(0);
        }
    }

    /**
     * Update a badge to a new one.
     *
     * @param badge new field values for badge
     * @param id id of badge to update
     * @return updated badge
     */
    @Override
    public Badge update(final Badge badge, final Integer id) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id)
            .addValue(DbQueryConstants.name.name(), badge.getName())
            .addValue(DbQueryConstants.script.name(), badge.getScript());
        template.update(updateBadge, param, holder, new String[]
            {DbQueryConstants.id.name()});
        badge.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return badge;
    }

    /**
     * Insert a badge in the database.
     *
     * @param badge badge to insert
     * @return inserted badge
     */
    @Override
    public Badge insert(final Badge badge) {
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.name.name(), badge.getName())
            .addValue(DbQueryConstants.script.name(), badge.getScript());
        template.update(insertBadge, param, holder, new String[]
            {DbQueryConstants.id.name()});
        badge.setId(Objects.requireNonNull(holder.getKey()).intValue());
        return badge;
    }

    /**
     * Delete a badge with specified ID from the database.
     *
     * @param id ID of badge to delete
     */
    @Override
    public void delete(final Integer id) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.id.name(), id);
        template.update(deleteBadge, param);
    }

    /**
     * Compute user's badges.
     *
     * @param userId the id of user to compute badges for
     * @return strings with names of user's badges
     */
    @Override
    public List<String> getUserBadges(final Integer userId) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.user_id.name(), userId);
        return template.query(getUserBadges, param, new StringMapper());
    }

    /**
     * Get a list of all users who would receive a badge with specified script.
     *
     * @param script a script to compute the badge
     * @return list of users to receive a badge with specified script
     */
    @Override
    public List<User> getUsersWithBadge(final String script) {
        SqlParameterSource param = new MapSqlParameterSource()
            .addValue(DbQueryConstants.script.name(), script);
        return template.query(getUsersWithBadge, param, new UserMapper());
    }

}
