package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Filter;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.ISearchService;
import com.meetup.utils.constants.ModelConstants;
import io.swagger.annotations.Api;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for Filters.
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

    /**
     * Perform filtered search.
     * @return list of matched meetups
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/search", params = {"pagesize", "page"})
    public ResponseEntity search(
         @RequestBody final Filter filter,
         @RequestParam("pagesize") final int pageSize,
         @RequestParam("page") final int currentPage
    )   {
        int offset = pageSize * (currentPage - 1);
        int count = searchService.getAllMeetupsCount(filter);
        List<MeetupDisplayDTO> meetups = searchService.getMeetups(filter, offset, pageSize);
        Map<Object, Object> model = new HashMap<>();
        model.put(ModelConstants.meetupCount, count);
        model.put(ModelConstants.meetups, meetups);
        return new ResponseEntity<>(model, HttpStatus.OK);
    }


    /**
     * Method to save a filter to current user. Identify current user by token.
     * @param token cookie value
     * @param filter Filter to save
     * @return saved Filter
     */
    @PreAuthorize("hasAnyAuthority(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/current/filters")
    public ResponseEntity<Filter> saveFilter(
        @CookieValue("token") final String token,
        @RequestBody Filter filter
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.createFilter(filter, id));
    }

    /**
     * Get user`s saved filters.
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
