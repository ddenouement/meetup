package com.meetup.service.impl;

import com.meetup.entities.Meetup;
import com.meetup.entities.dto.UserDTO;
import com.meetup.error.UserNotFoundException;
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
    public Map<Object, Object> getOtherUserProfile(final String login) {
        Map<Object, Object> model = new HashMap<>();
        UserDTO user;
        try {
            user = userService.getProfileUserDTO(login);
        } catch (UserNotFoundException a) {
            return model;
        }
        model.put(ModelConstants.USERDTO, user);
        model.put(ModelConstants.SUBSCRIPTIONS,
            userService.getUsersSubscriptionsToSpeakers(
                user.getId()));
        Pair<List<Meetup>, List<Meetup>> hosted =
            meetupService.getSpeakerMeetupsByLogin(user.getLogin());
        List<Meetup> hostedMeetupsPast = hosted.getFirst();
        List<Meetup> hostedMeetupsFuture = hosted.getSecond();
        model.put(ModelConstants.HOSTED_MEETUPS_FUTURE, hostedMeetupsFuture);
        model.put(ModelConstants.HOSTED_MEETUPS_PAST, hostedMeetupsPast);
        //we don`t send future joined, as part of privacy
        Pair<List<Meetup>, List<Meetup>> joined =
            meetupService.getUserJoinedMeetups(user.getId());
        List<Meetup> userJoinedMeetupsPast = joined.getFirst();
        model.put(ModelConstants.JOINED_MEETUPS_PAST, userJoinedMeetupsPast);
        List<String> badges = badgeService.getUserBadges(user.getId());
        model.put(ModelConstants.BADGES, badges);
        return model;
    }

}