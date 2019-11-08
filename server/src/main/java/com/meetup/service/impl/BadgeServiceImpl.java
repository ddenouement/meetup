package com.meetup.service.impl;

import com.meetup.entities.Badge;
import com.meetup.entities.User;
import com.meetup.error.BadgeNameExistsException;
import com.meetup.error.BadgeScriptIsIncorrectException;
import com.meetup.repository.IBadgeDAO;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IBadgeService;
import com.meetup.utils.Role;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BadgeServiceImpl implements IBadgeService {

    /**
     * Badge repository.
     */
    private IBadgeDAO badgeDAO;
    /**
     * User operations.
     */
    private IUserDAO userDAO;

    /**
     * The only prefix a badge script can have to be valid.
     */
    private static final String ALLOWED_BADGE_SCRIPT_START = "select";

    /**
     * Initialize service.
     *
     * @param badgeDAO badge repository.
     */
    @Autowired
    public BadgeServiceImpl(final IBadgeDAO badgeDAO, final IUserDAO userDAO) {
        this.badgeDAO = badgeDAO;
        this.userDAO = userDAO;
    }

    /**
     * Return a badge with specified ID.
     *
     * @param id ID of badge to return
     * @return a badge with specified ID
     */
    @Override
    public Badge getById(final Integer id) {
        return badgeDAO.findById(id);
    }

    /**
     * Return all badges.
     *
     * @return a list of all badges
     */
    @Override
    public List<Badge> getAll() {
        return badgeDAO.findAll();
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
        checkScript(badge.getScript());
        Badge oldBadge = badgeDAO.findById(id);
        if (!oldBadge.getName().toLowerCase()
            .equals(badge.getName().toLowerCase())
            && badgeDAO.findByName(badge.getName()) != null) {
            throw new BadgeNameExistsException();
        }
        return badgeDAO.update(badge, id);
    }

    /**
     * Insert a badge.
     *
     * @param badge badge to insert
     * @return inserted badge
     */
    @Override
    public Badge insert(final Badge badge) {
        checkScript(badge.getScript());
        if (badgeDAO.findByName(badge.getName()) != null) {
            throw new BadgeNameExistsException();
        }
        return badgeDAO.insert(badge);
    }

    /**
     * Delete a badge with specified ID.
     *
     * @param id ID of badge to delete
     */
    @Override
    public void delete(final Integer id) {
        badgeDAO.delete(id);
    }

    /**
     * Compute user's badges.
     *
     * @param userId the id of user to compute badges for
     * @return strings with names of user's badges
     */
    @Override
    public List<String> getUserBadges(final Integer userId) {
        return badgeDAO.getUserBadges(userId);
    }

    /**
     * Get a list of all users who would receive a badge with specified script.
     *
     * @param script a script to compute the badge
     * @return list of users to receive a badge with specified script
     */
    @Override
    public List<User> getUsersWithBadge(final String script) {
        if (!script.toLowerCase().startsWith(ALLOWED_BADGE_SCRIPT_START)) {
            throw new BadgeScriptIsIncorrectException();
        }
        try {
            return badgeDAO.getUsersWithBadge(script)
                .stream()
                .map(this::fillRoles)
                .collect(Collectors.toList());
        } catch (Exception e) {
            throw new BadgeScriptIsIncorrectException();
        }
    }

    private User fillRoles(final User user) {
        for (Role role : userDAO.findUserRolesByLogin(user.getLogin())) {
            user.addRole(role);
        }
        return user;
    }

    /**
     * Throws BadgeScriptIsIncorrectException if script is incorrect.
     *
     * @param script script to check
     */
    private void checkScript(final String script) {
        getUsersWithBadge(script);
    }
}
