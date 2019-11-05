package com.meetup.controller;

import com.meetup.entities.Topic;
import com.meetup.service.ITopicService;
import com.meetup.service.impl.TopicServiceImpl;
import io.swagger.annotations.Api;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Operations used to manage topics.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class TopicController {

    /**
     * Service, that manages topics.
     */
    private ITopicService topicService;

    /**
     * Set the MeetupService.
     *
     * @param topicService TopicService.
     */
    TopicController(@Autowired final TopicServiceImpl topicService) {
        this.topicService = topicService;
    }

    /**
     * Get all topics mapping.
     *
     * @return Response entity with list of all topics.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/meetups/topics")
    public ResponseEntity<List<Topic>> getAvailableTopics() {
        return new ResponseEntity<>(topicService.getAllTopics(),
            HttpStatus.OK);
    }

    /**
     * Get topic.
     *
     * @param topicID topic ID.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @GetMapping("/meetups/topics/{id}")
    public ResponseEntity<Topic> getTopic(
        @PathVariable("id") final int topicID) {
        return new ResponseEntity<>(topicService.getTopic(topicID),
            HttpStatus.OK);
    }

    /**
     * Create topic.
     *
     * @param topic Created topic.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PostMapping("/meetups/topics")
    public ResponseEntity<Topic> createTopic(
        @RequestBody final Topic topic) {
        return new ResponseEntity<>(topicService.createTopic(topic),
            HttpStatus.CREATED);
    }

    /**
     * Update topic.
     *
     * @param topicID topic ID to be edited.
     * @param topic edited topic.
     * @return Response entity with topic.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @PutMapping("/meetups/topics/{id}")
    public ResponseEntity<Topic> updateTopic(
        @PathVariable("id") final int topicID,
        @RequestBody final Topic topic) {
        return new ResponseEntity<>(topicService.updateTopic(topicID, topic),
            HttpStatus.OK);
    }

    /**
     * Remove topic.
     *
     * @param topicID topic ID to be removed.
     * @return Response entity with status code.
     */
    @PreAuthorize("hasAnyRole(T(com.meetup.utils.Role).ADMIN, "
        + "T(com.meetup.utils.Role).SPEAKER, "
        + "T(com.meetup.utils.Role).LISTENER)")
    @DeleteMapping("/meetups/topics/{id}")
    public ResponseEntity removeTopic(
        @PathVariable("id") final int topicID) {
        topicService.removeTopic(topicID);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
