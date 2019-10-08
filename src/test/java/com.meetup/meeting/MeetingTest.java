package com.meetup.meeting;

import com.meetup.entities.Meeting;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class MeetingTest {
    @Test
    public void equalsTest(){
        Meeting meeting = Meeting.builder()
                .duration(Duration.ofHours(1))
                .date(LocalDateTime.of(2019,10,6,12,0))
                .build();

        Meeting meeting2 = Meeting.builder()
                .duration(Duration.ofHours(1))
                .date(LocalDateTime.of(2019,10,6,12,0))
                .build();

        Assert.assertEquals(meeting,meeting2);
    }

    @Test
    public void hashCodeTest(){
        Meeting meeting = Meeting.builder().build();
        Meeting meeting2 = Meeting.builder().build();
        Assert.assertEquals(meeting.hashCode(), meeting2.hashCode());
    }


}
