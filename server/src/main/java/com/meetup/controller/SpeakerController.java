package com.meetup.controller;

import com.meetup.entities.Meetup;
import com.meetup.entities.dto.ArticleCreationDTO;
import com.meetup.service.impl.ArticleServiceImpl;
import com.meetup.service.impl.LoginValidatorServiceImpl;
import com.meetup.service.impl.MeetupServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * API Rest Controller for speaker functionality.
 */
@RestController
@Api(value = "meetup-application", description = "Operations used to manage speaker functionality")
public class SpeakerController {

    /**
     * Meetup service.
     */
    private MeetupServiceImpl meetupService;

    /**
     * Article service.
     */
    private ArticleServiceImpl articleService;

    /**
     * Login validator service service.
     */
    private LoginValidatorServiceImpl loginValidatorService;

    /**
     * SpeakerController constructor.
     *
     * @param meetupService MeetupService param.
     * @param loginValidatorService LoginValidatorService.
     * @param articleService ArticleService.
     */
    SpeakerController(@Autowired final MeetupServiceImpl meetupService,
        @Autowired final LoginValidatorServiceImpl loginValidatorService,
        @Autowired final ArticleServiceImpl articleService) {
        this.meetupService = meetupService;
        this.loginValidatorService = loginValidatorService;
        this.articleService = articleService;
    }

    /**
     * Create meetup.
     *
     * @param token JSON web token.
     * @param meetup Meetup object to be created.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @PostMapping(value = "/api/v1/user/speaker/meetups")
    public ResponseEntity createMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup meetup) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.createMeetup(meetup, userLogin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Update existing meetup.
     *
     * @param token JSON web token.
     * @param updatedMeetup Meetup to be updated.
     * @param meetupID Meetup ID to be canceled.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @PutMapping(value = "/api/v1/user/speaker/meetups/{id}")
    public ResponseEntity updateMeetup(
        @CookieValue("token") final String token,
        @RequestBody final Meetup updatedMeetup,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.updateMeetup(meetupID, updatedMeetup, userLogin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Cancel existing meetup.
     *
     * @param token JSON web token.
     * @param meetupID Meetup to be canceled.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @DeleteMapping(value = "/api/v1/user/speaker/meetups/{id}")
    public ResponseEntity cancelMeetup(
        @CookieValue("token") final String token,
        @PathVariable("id") final int meetupID) {
        String userLogin = loginValidatorService.extractLogin(token);
        meetupService.cancelMeetup(meetupID, userLogin);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Create and post new Article from speaker.
     *
     * @param token JSON web token.
     * @param articleCreationDTO ArticleDTO that should be created
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @PostMapping(value = "api/v1/user/speaker/articles")
    public ResponseEntity postArticle(
        @CookieValue("token") final String token,
        @RequestBody final ArticleCreationDTO articleCreationDTO) {
        String userLogin = loginValidatorService.extractLogin(token);
        articleService.postArticle(articleCreationDTO, userLogin);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Remove article by speaker.
     *
     * @param token JSON web token.
     * @param articleID Article ID that should be removed.
     * @return ResponseEntity with status code.
     */
    @PreAuthorize("hasRole(T(com.meetup.entities.Role).SPEAKER)")
    @DeleteMapping(value = "api/v1/user/speaker/articles/{id}")
    public ResponseEntity removeArticle(
        @CookieValue("token") final String token,
        @PathVariable("id") final int articleID) {
        String userLogin = loginValidatorService.extractLogin(token);
        articleService.removeArticle(articleID, userLogin);
        return new ResponseEntity(HttpStatus.OK);
    }
}
