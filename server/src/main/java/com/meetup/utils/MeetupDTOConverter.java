package com.meetup.utils;

import com.meetup.entities.Meetup;
import com.meetup.entities.MeetupState;
import com.meetup.entities.User;
import com.meetup.entities.dto.MeetupDisplayDTO;
import com.meetup.entities.dto.UserDTO;
import com.meetup.repository.impl.LanguageDaoImpl;
import com.meetup.repository.impl.UserDaoImpl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**.
 * Class to convert a Meetup exemplar to MeetupDisplayDTO exemplar
 */
@Component
public class MeetupDTOConverter {

    /**
     * User repository.
     */
    private UserDaoImpl userDao;
    /**
     * Language repository.
     */
    private LanguageDaoImpl languageDao;

    /**
     * Constructor.
     * @param languageDao
     * Language repository.
     * @param userDao
     * User repository.
     */
    MeetupDTOConverter(@Autowired final LanguageDaoImpl languageDao,
        @Autowired final UserDaoImpl userDao) {
        this.languageDao = languageDao;
        this.userDao = userDao;
    }

    /**
     * Method, used to transform Meetup object into MeetupDisplayDTO.
     * @param meetup
     * Meetup.
     * @return
     * MeetupDisplayDTO.
     */
    public MeetupDisplayDTO convertToMeetupDTO(final Meetup meetup) {

        UserDTOConverter userDTOConverter = new UserDTOConverter();
        User speaker = userDao.findUserById(meetup.getSpeakerId());
        UserDTO speakerDTO = userDTOConverter.convertToUserDTO(speaker);
        MeetupDisplayDTO newMeetup = new MeetupDisplayDTO();

        newMeetup.setId(meetup.getId());
        newMeetup.setSpeaker(speakerDTO);
        newMeetup.setLanguage(languageDao.findLanguageByID(meetup.getLanguageId()));
        newMeetup.setState(MeetupState.getStateByID(meetup.getStateId()));
        newMeetup.setTitle(meetup.getTitle());
        newMeetup.setStartDate(meetup.getStartDate());
        newMeetup.setDurationMinutes(meetup.getDurationMinutes());
        newMeetup.setMinAttendees(meetup.getMinAttendees());
        newMeetup.setMaxAttendees(meetup.getMaxAttendees());
        newMeetup.setDescription(meetup.getDescription());
        newMeetup.setTopics(meetup.getTopics());
        return newMeetup;
    }

    /**
     * Method, used to transform List<Meetup> object
     * into List<MeetupDisplayDTO>.
     * @param meetupList
     * Meetups list.
     * @return
     * MeetupDisplayDTO list.
     */
    public List<MeetupDisplayDTO> convertToMeetupDTO(final List<Meetup> meetupList) {
        List<MeetupDisplayDTO> meetupDTOList = new ArrayList<>();
        for (Meetup meetup: meetupList) {
            meetupDTOList.add(convertToMeetupDTO(meetup));
        }
        return meetupDTOList;
    }
}
