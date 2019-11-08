package com.meetup.service;

import com.meetup.entities.Badge;
import com.meetup.entities.User;
import java.util.List;

/**
 * Interface to work with badges in the database.
 */
public interface IBadgeService {

    /**
     * Return a badge with specified ID.
     * @param id ID of badge to return
     * @return a badge with specified ID
     */
    Badge getById(Integer id);

    /**
     * Return all badges.
     * @return a list of all badges
     */
    List<Badge> getAll();

    /**
     * Update a badge to a new one.
     * @param badge new field values for badge
     * @param id id of badge to update
     * @return updated badge
     */
    Badge update(Badge badge, Integer id);

    /**
     * Insert a badge.
     * @param badge badge to insert
     * @return inserted badge
     */
    Badge insert(Badge badge);

    /**
     * Delete a badge with specified ID.
     * @param id ID of badge to delete
     */
    void delete(Integer id);

    /**
     * Compute user's badges.
     * @param userId the id of user to compute badges for
     * @return strings with names of user's badges
     */
    List<String> getUserBadges(Integer userId);

    /**
     * Get a list of all users who would receive a badge with specified script.
     * @param script a script to compute the badge
     * @return list of users to receive a badge with specified script
     */
    List<User> getUsersWithBadge(String script);
}
