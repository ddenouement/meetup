package com.meetup.service.impl;

import com.meetup.entities.Meeting;
import com.meetup.entities.Topic;
import com.meetup.entities.User;
import com.meetup.repository.impl.MeetingDaoImpl;
import com.meetup.repository.impl.TopicDaoImpl;
import com.meetup.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MeetingServiceImpl implements MeetingService {

    @Autowired
    TopicDaoImpl topicDao;
    @Autowired
    MeetingDaoImpl meetingDao;

    @Override
    public List<Topic> getAllTopics() {
        return topicDao.getAllTopics();
    }

    @Override
    public List<Meeting> getAllMeetings() {
        return meetingDao.getAllMeetings();
    }

    @Override
    public ResponseEntity<String> createMeeting(Meeting meeting, User user) {
        meetingDao.insertNewMeeting(meeting);
        return null;
    }

    @Override
    public List<Meeting> getSpeakerMeetings(User user) {
        return meetingDao.getSpeakerMeetings(user.getId());
    }
}
