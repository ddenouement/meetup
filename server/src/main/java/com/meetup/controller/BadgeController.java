package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Badge;
import com.meetup.entities.User;
import com.meetup.service.IBadgeService;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest api for badges.
 */
@RestController
@Api(value = "meetup-application")
public class BadgeController {

    /**
     * Badge operations.
     */
    private IBadgeService badgeService;

    /**
     * Initialixe controller.
     *
     * @param badgeService badge operations
     */
    public BadgeController(final IBadgeService badgeService) {
        this.badgeService = badgeService;
    }

    /**
     * Return a list of all badges.
     *
     * @return a list of badges
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping("/api/v1/badges")
    public ResponseEntity<List<Badge>> getBadges() {
        return ok(badgeService.getAll());
    }

    /**
     * Return a badge with specified id.
     *
     * @param id id of badge to return
     * @return a badge
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping("/api/v1/badge/{id}")
    public ResponseEntity<Badge> getBadgeById(
        @PathVariable("id") final Integer id) {
        Badge badge = badgeService.getById(id);
        if (badge == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ok(badge);
    }

    /**
     * Insert a badge.
     *
     * @param badge badge to insert
     * @return inserted badge
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping("/api/v1/badge")
    public ResponseEntity<Badge> insertBadge(@RequestBody final Badge badge) {
        return new ResponseEntity<>(badgeService.insert(badge),
            HttpStatus.CREATED);
    }

    /**
     * Update a badge with specified id.
     *
     * @param id id of badge to update
     * @param badge updated badge
     * @return updated badge
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PutMapping("/api/v1/badge/{id}")
    public ResponseEntity<Badge> updateBadge(
        @PathVariable("id") final Integer id,
        @RequestBody final Badge badge) {
        return new ResponseEntity<>(badgeService.update(badge, id),
            HttpStatus.OK);
    }

    /**
     * Delete a badge with specified id.
     *
     * @param id id of badge to delete
     * @return status
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @DeleteMapping("/api/v1/badge/{id}")
    public ResponseEntity deleteBadge(@PathVariable("id") final Integer id) {
        badgeService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Get badges for user with specified id.
     *
     * @param id id of user to get badges for
     * @return a list of badges for user
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/api/v1/user/{id}/badges")
    public ResponseEntity<List<String>> getUserBadges(
        @PathVariable("id") final Integer id) {
        return ok(badgeService.getUserBadges(id));
    }

    /**
     * Get users that would receive a badge with specified script. If script is
     * incorrect, return an error.
     *
     * @param script script for badge
     * @return a list of badges for user
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping("/api/v1/badge/check")
    public ResponseEntity<List<User>> checkBadgeScript(
        @RequestBody final String script) {
        return ok(badgeService.getUsersWithBadge(script));
    }
}
