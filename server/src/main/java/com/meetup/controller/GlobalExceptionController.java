package com.meetup.controller;

import com.meetup.error.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception controller class.
 * Returns ResponseEntity object with status code.
 */

//TODO constants, logger
@ControllerAdvice
public class GlobalExceptionController {

    /**
     * MeetupNotFoundException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(MeetupNotFoundException.class)
    public ResponseEntity<Object> meetupNotFoundException() {
        return new ResponseEntity<>(
            "Meetup not found!",
            HttpStatus.NOT_FOUND);
    }

    /**
     * TopicNotFoundException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(TopicNotFoundException.class)
    public ResponseEntity<Object> topicNotFoundException() {
        return new ResponseEntity<>(
            "Topic not found!",
            HttpStatus.NOT_FOUND);
    }

    /**
     * ArticleNotFoundException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<Object> articleNotFoundException() {
        return new ResponseEntity<>(
            "Article not found!",
            HttpStatus.NOT_FOUND);
    }

    /**
     * OutOfSlotsException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(OutOfSlotsException.class)
    public ResponseEntity<Object> outOfSlotsException() {
        return new ResponseEntity<>(
            "No available slot for listener!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * SpeakerOperationNotAllowedException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(SpeakerOperationNotAllowedException.class)
    public ResponseEntity<Object> speakerOperationNotAllowedException() {
        return new ResponseEntity<>(
            "You are not allowed to do this operation!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * EmailIsUsedException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(EmailIsUsedException.class)
    public ResponseEntity<Object> emailIsUsedException() {
        return new ResponseEntity<>(
            "E-mail is already used!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * LoginIsUsedException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(LoginIsUsedException.class)
    public ResponseEntity<Object> loginIsUsedException() {
        return new ResponseEntity<>(
            "Login is already used!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * TopicIsUsedException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(TopicIsUsedException.class)
    public ResponseEntity<Object> topicIsUsedException() {
        return new ResponseEntity<>(
            "Topic with this name already exists!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * LanguageIsUsedException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler(LanguageIsUsedException.class)
    public ResponseEntity<Object> languageIsUsedException() {
        return new ResponseEntity<>(
            "Language with this name already exists!",
            HttpStatus.FORBIDDEN);
    }

    /**
     * AuthenticationException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> authenticationException() {
        return new ResponseEntity<>(
            "Invalid username/password",
            HttpStatus.UNAUTHORIZED);
    }

    /**
     * BadgeScriptIsIncorrectException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({BadgeScriptIsIncorrectException.class})
    public ResponseEntity<Object> badgeScriptIsIncorrectException() {
        return new ResponseEntity<>(
            "SQL script is incorrect.",
            HttpStatus.BAD_REQUEST);
    }

    /**
     * BadgeNameExistsException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({BadgeNameExistsException.class})
    public ResponseEntity<Object> badgeNameExistsException() {
        return new ResponseEntity<>(
            "Badge with such name already exists.",
            HttpStatus.BAD_REQUEST);
    }

    /**
     * AuthenticationException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> userNotFoundException() {
        return new ResponseEntity<>(
                "No user found",
                HttpStatus.FORBIDDEN);
    }

    /**
     * IllegalMeetupStateException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({IllegalMeetupStateException.class})
    public ResponseEntity<Object> illegalMeetupStateException() {
        return new ResponseEntity<>(
            "Such meetup operation with this state is prohibited!",
            HttpStatus.BAD_REQUEST);
    }

    /**
     * EmailDoesntExistException controller.
     * @return
     * Response entity with status code.
     */
    @ExceptionHandler({EmailDoesntExistException.class})
    public ResponseEntity<Object> emailDoesntExistException() {
        return new ResponseEntity<>(
                "User with this email doesn't exist!",
            HttpStatus.BAD_REQUEST);
    }

}
