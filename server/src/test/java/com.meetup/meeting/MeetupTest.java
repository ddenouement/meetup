package com.meetup.meeting;

import com.meetup.entities.Filter;
import com.meetup.entities.Meetup;
import com.meetup.repository.impl.SearchDaoImpl;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
@Component
public class MeetupTest   {


    @Test
    public void hashCodeTest() {
        Meetup meetup = Meetup.builder().build();
        Meetup meetup2 = Meetup.builder().build();
        Assert.assertEquals(meetup.hashCode(), meetup2.hashCode());
    }


}
