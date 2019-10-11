package com.meetup.entities;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meeting {

    private int id;
    private int speakerId;
    private int languageId;
    private String title;
    private LocalDateTime date;
    private Duration duration;
    private int minAttendees;
    private int maxAttendees;
    private int state;
    private String description;
    private List<Topic> topics;

}
