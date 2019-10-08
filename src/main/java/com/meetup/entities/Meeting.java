package com.meetup.entities;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Meeting {

    private int id;
    private String title;
    private LocalDateTime date;
    private Duration duration;
    private int minAttendees;
    private int maxAttendees;
    private State state;
    private String description;
    private List<Topic> topics;

}
