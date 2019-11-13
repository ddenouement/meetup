package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Filter;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.ISearchService;
import io.swagger.annotations.Api;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest Controller for Filters.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class FilterController {

    /**
     * Login validation service.
     */
    private ILoginValidatorService loginValidatorService;
    /**
     * Search service.
     */
    private ISearchService searchService;

    /**
     * Constructor.
     *
     * @param searchService SearchService.
     */
    @Autowired
    public FilterController(final ISearchService searchService,
        final ILoginValidatorService loginValidatorService) {
        this.searchService = searchService;
        this.loginValidatorService = loginValidatorService;
    }
//TODO sql exception
    /**
     * Perform filtered search.
     *
     * @return list of matched meetups
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/search")
    public ResponseEntity<List<MeetupDisplayDTO>> search(
         @RequestBody final Filter filter
    ) throws SQLException {

        return ok(searchService.getMeetups(filter));
    }


    /**
     * user can save filter.
     *
     * @param token cookie value
     * @param filter Filter to save
     * @return saved Filter
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/current/filters")
    public ResponseEntity<Filter> createFilter(
        @CookieValue("token") final String token,
        @RequestBody Filter filter
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.createFilter(filter, id));
    }

    /**
     * Return saved user`s filters.
     *
     * @param token cookie value
     * @return saved Filters list
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/current/filters")
    public ResponseEntity<List<Filter>> getFilters(
        @CookieValue("token") final String token
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.getFilters(id));
    }
}
