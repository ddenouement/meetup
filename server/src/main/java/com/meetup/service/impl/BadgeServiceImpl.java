package com.meetup.service.impl;

import com.meetup.entities.Badge;
import com.meetup.entities.User;
import com.meetup.error.BadgeScriptIsIncorrectException;
import com.meetup.repository.IBadgeDAO;
import com.meetup.service.IBadgeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeServiceImpl implements IBadgeService {

    /** Badge repository. */
    private IBadgeDAO badgeDAO;

    /**
     * Initialize service.
     * @param badgeDAO badge repository.
     */
    @Autowired
    public BadgeServiceImpl(final IBadgeDAO badgeDAO) {
        this.badgeDAO = badgeDAO;
    }

    /**
     * Return a badge with specified ID.
     * @param id ID of badge to return
     * @return a badge with specified ID
     */
    @Override
    public Badge getById(final Integer id) {
        return badgeDAO.findById(id);
    }

    /**
     * Return all badges.
     * @return a list of all badges
     */
    @Override
    public List<Badge> getAll() {
        return badgeDAO.findAll();
    }

    /**
     * Update a badge to a new one.
     * @param badge new field values for badge
     * @param id id of badge to update
     * @return updated badge
     */
    @Override
    public Badge update(final Badge badge, final Integer id) {
        checkScript(badge.getScript());
        return badgeDAO.update(badge, id);
    }

    /**
     * Insert a badge.
     * @param badge badge to insert
     * @return inserted badge
     */
    @Override
    public Badge insert(final Badge badge) {
        checkScript(badge.getScript());
        return badgeDAO.insert(badge);
    }

    /**
     * Delete a badge with specified ID.
     * @param id ID of badge to delete
     */
    @Override
    public void delete(final Integer id) {
        badgeDAO.delete(id);
    }

    /**
     * Compute user's badges.
     * @param userId the id of user to compute badges for
     * @return strings with names of user's badges
     */
    @Override
    public List<String> getUserBadges(final Integer userId) {
        return badgeDAO.getUserBadges(userId);
    }

    /**
     * Get a list of all users who would receive a badge with specified script.
     * @param script a script to compute the badge
     * @return list of users to receive a badge with specified script
     */
    @Override
    public List<User> getUsersWithBadge(final String script) {
        try {
            return badgeDAO.getUsersWithBadge(script);
        } catch (Exception e) {
            throw new BadgeScriptIsIncorrectException();
        }
    }

    /**
     * Throws BadgeScriptIsIncorrectException if script is incorrect.
     * @param script script to check
     */
    private void checkScript(final String script) {
        getUsersWithBadge(script);
    }
}
