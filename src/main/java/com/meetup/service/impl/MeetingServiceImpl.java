package com.meetup.service.impl;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.MeetingDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Dmytro Zubko
 */

@Component
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    TopicDaoImpl topicDao;
    @Autowired
    MeetingDaoImpl meetingDao;

    /**
     * Method used to get topics list from database, using topic repository (TopicDao)
     * @return
     * List of "Topic" objects
     */
    @Override
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    /**
     * Method used to get meetings list from database, using meeting repository (MeetingDao)
     * @return
     * List of "Meeting" objects
     */
    @Override
    public List<Meeting> getAllMeetings() {
        return meetingDao.getAllMeetings();
    }

    /**
     *
     * @param meeting
     * Object, to be added to database
     * @param user
     * User (Speaker) that creates a meeting
     * @return
     * Entity, representing information about creating meeting status
     */
    @Override
    public ResponseEntity<String> createMeeting(Meeting meeting, User user) {
        //TODO Find out, if speaker required for meeting creation
        meetingDao.insertNewMeeting(meeting);
        return new ResponseEntity<>("Successful created", HttpStatus.CREATED);
    }

    /**
     * Method used to get specific speaker meetings list from database, using meeting repository (MeetingDao)
     * @return
     * List of "Meeting" objects
     */
    @Override
    public List<Meeting> getSpeakerMeetings(User user) {
        return meetingDao.getSpeakerMeetings(user.getId());
    }
}
