package com.meetup.meeting;

import com.meetup.entities.Meetup;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class MeetupTest {
    @Test
    public void equalsTest() {
        Meetup meetup = Meetup.builder()
                .duration(Duration.ofHours(1))
                .startDate(LocalDateTime.of(2019, 10, 6, 12, 0))
                .build();

        Meetup meetup2 = Meetup.builder()
                .duration(Duration.ofHours(1))
                .startDate(LocalDateTime.of(2019, 10, 6, 12, 0))
                .build();

        Assert.assertEquals(meetup, meetup2);
    }

    @Test
    public void hashCodeTest() {
        Meetup meetup = Meetup.builder().build();
        Meetup meetup2 = Meetup.builder().build();
        Assert.assertEquals(meetup.hashCode(), meetup2.hashCode());
    }


}
