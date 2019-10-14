package com.meetup.controller;

import com.meetup.entities.User;
import com.meetup.entities.UserDTO;
import com.meetup.service.IUserService;
import com.meetup.service.RoleProcessor;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;


@RestController
@Api(value = "meetup-application", description = "Operations used to manage user functionality")
public class UserController {

    @Autowired
    private IUserService IUserService;


    //TODO implement
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "/api/v1/user/profile")
    public ResponseEntity<UserDTO>  getUserProfile(@RequestParam String login) {
        //TODO: make model
       /* Map<Object, Object> model = new HashMap<>();
        model.put("UserDTO", IUserService.getProfileUserDTO(login));
        if(RoleProcessor.isSpeaker(us)) {
            model.put("hostedMeetups", meetupDao.getSpeakerMeetups(us.getId()));
        }
        model.put("token", token);

        return ok(model);*/
         return new ResponseEntity<>(IUserService.getProfileUserDTO(login),
            HttpStatus.OK);
    }

    //TODO implement
    @PreAuthorize("hasAnyRole('ADMIN','SPEAKER','LISTENER')")
    @GetMapping(value = "/api/v1/user/availableSpeakers")
    public ResponseEntity<List<User>> getAllSpeakers() {
        return new ResponseEntity<>(IUserService.getAllSpeakers(),
            HttpStatus.OK);
    }

}
