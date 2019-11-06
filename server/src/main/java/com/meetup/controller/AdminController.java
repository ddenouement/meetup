package com.meetup.controller;

import com.meetup.service.IUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Operations used to manage admin functionality.
 */
@RestController
@Api(value = "meetup-application")
@RequestMapping("/api/v1")
public class AdminController {
    /**
     * Operations with users.
     */
    private IUserService userService;
    /**
     * Constructor.
     *
     * @param userService user operations
     */
    @Autowired
    public AdminController(final IUserService userService) {
        this.userService = userService;
    }

    /**
     * Admin can deactivate user by his Id.
     *
     * @param id user's id
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/user/users/{id}/deactivate")
    public ResponseEntity deactivateUser(
        @PathVariable("id") final int id) {
        userService.deactivateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Admin can activate user by his Id.
     *
     * @param id user's id
     * @return status
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/users/{id}/activate")
    public ResponseEntity activateUser(final @PathVariable("id") int id) {
        userService.activateUser(id);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    /**
     * Admin can see all complaints.
     *
     * @return ResponseEntity with list
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @GetMapping(value = "/user/complaints")
    public ResponseEntity getAllComplaints() {
        return new ResponseEntity<>(
            userService.getAllNotReadComplaints(), HttpStatus.OK);
    }

    /**
     * Admin can mark complaint as read.
     *
     * @param complaintID ID of complaint.
     * @return ResponseEntity
     */
    @PreAuthorize("hasRole(T(com.meetup.utils.Role).ADMIN)")
    @PostMapping(value = "/user/complaints/{id}/read")
    public ResponseEntity markAsReadComplaint(
        @PathVariable("id") final int complaintID) {
        return new ResponseEntity<>(
            userService.markAsReadComplaint(complaintID), HttpStatus.OK);
    }
}
