package com.meetup.service.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.dto.UserDTO;
import com.meetup.error.UserNotFoundException;
import com.meetup.repository.IUserDAO;
import com.meetup.service.IBadgeService;
import com.meetup.service.IMeetupService;
import com.meetup.service.IProfileService;
import com.meetup.service.IUserService;
import com.meetup.utils.ModelConstants;
import com.meetup.utils.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * .
 */
@Component
public class ProfileService implements IProfileService {

    @Autowired
    private IUserDAO userDAO;
    /**
     * .
     */
    @Autowired
    private IBadgeService badgeService;
    /**
     * .
     */
    @Autowired
    private IUserService userService;

    /**
     * .
     */
    @Autowired
    private IMeetupService meetupService;

    /**
     * . Other user profile doesn`t include future joined meetups.
     *
     * @return Map of objects, that characterize user
     */
    @Override
    public Map<Object, Object> getOtherUserProfile(final int id) {
        Map<Object, Object> model = new HashMap<>();
        //throws UserNotFoundException
        UserDTO
            user = userService.getProfileUserDTO(id);

        user.setLanguages(userDAO.getUsersLanguages(id));
        model.put(ModelConstants.userDTO, user);
        model.put(ModelConstants.subscribedTo,
            userService.getUsersSubscriptionsToSpeakers(
                user.getId()));
        List<Meetup> hostedMeetupsPast =
                meetupService.getHostedMeetupsPast(user.getId());
        List<Meetup> hostedMeetupsFuture =
                meetupService.getHostedMeetupsFuture(user.getId());
        model.put(ModelConstants.hostedMeetupsFuture, hostedMeetupsFuture);
        model.put(ModelConstants.hostedMeetupsPast,hostedMeetupsPast);
        //we don`t send future joined, as part of privacy
        List<Meetup> userJoinedMeetupsPast =
                meetupService.getJoinedMeetupsPast(user.getId());
        model.put(ModelConstants.joinedMeetupsPast, userJoinedMeetupsPast);
        List<String> badges = badgeService.getUserBadges(user.getId());
        model.put(ModelConstants.badges , badges);
        return model;
    }

}
