package com.meetup.entities;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
public class Meeting {

    @Builder.Default
    private String title = "My meeting";
    @Builder.Default
    private LocalDateTime date = LocalDateTime.now().plusHours(1);
    @Builder.Default
    private Duration duration = Duration.ofHours(1);
    @Builder.Default
    private int minAttendees = 1;
    @Builder.Default
    private int maxAttendees = 1000;
    @Builder.Default
    private State state = State.SCHEDULED;
    @Builder.Default
    private String description = "Another interesting meeting";
    @Builder.Default
    private List<Topic> topics = Arrays.asList(new Topic("General"));

}
