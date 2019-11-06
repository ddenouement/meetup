package com.meetup.controller;

import static org.springframework.http.ResponseEntity.ok;

import com.meetup.entities.Filter;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.service.ILoginValidatorService;
import com.meetup.service.ISearchService;
import io.swagger.annotations.Api;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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

    /**
     * Perform filtered search.
     *
     * @return list of matched meetups
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/search")
    public ResponseEntity<List<MeetupDisplayDTO>> searchWithFilter(
        @RequestBody final Filter filter
    ) {
        return ok(searchService.searchWithFilter(filter));
    }

    //only for testing, to see how JSON looks like
    @GetMapping(value = "/users/filter")
    public ResponseEntity<Filter> getSampleFilter(
    ) {
        Filter filter = new Filter();
        filter.setRate_to(5);
        filter.setTopics_ids(Arrays.asList(2, 3));
        filter.setId_language(2);
        filter.setId_user(2);//petrenko
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy/MM/dd").parse("2019/12/14");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        filter.setTime_to(d);
        return ok(filter);
    }

    /**
     * user can save filter.
     *
     * @param token cookie value
     * @param filter Filter to save
     * @return saved Filter
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping(value = "/users/current/filters")
    public ResponseEntity<Filter> saveFilter(
        @CookieValue("token") final String token,
        @RequestBody Filter filter
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.insertFilter(filter, id));
    }

    /**
     * Return saved user`s filters.
     *
     * @param token cookie value
     * @return saved Filters list
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping(value = "/users/current/filters")
    public ResponseEntity<List<Filter>> savedFilters(
        @CookieValue("token") final String token
    ) {
        int id = loginValidatorService.extractId(token);
        return ok(searchService.getUserFilters(id));
    }
}
