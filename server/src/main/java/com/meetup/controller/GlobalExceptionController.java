package com.meetup.controller;

import com.meetup.error.EmailIsUsedException;
import com.meetup.error.LoginIsUsedException;
import com.meetup.error.MeetupNotFoundException;
import com.meetup.error.OutOfSlotsException;
import com.meetup.error.SpeakerOperationNotAllowedException;
import com.meetup.error.TopicNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception controller class.
 * Returns ResponseEntity object with status code.
 */
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

}
