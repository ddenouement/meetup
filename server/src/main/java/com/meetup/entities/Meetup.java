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

/**
 * Model class of Meetup.
 */
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Meetup {

    /**
     * ID of meetup.
     */
    private int id;
    /**
     * Speaker ID of meetup.
     */
    private int speakerId;
    /**
     * Language ID of meetup.
     */
    private int languageId;
    /**
     * State ID of meetup.
     */
    private int stateId;
    /**
     * Title of meetup.
     */
    private String title;
    /**
     * Start date and time of meetup.
     */
    private LocalDateTime startDate;
    /**
     * Duration of meetup.
     */
    private Duration duration;
    /**
     * Minimum number of attendees of meetup.
     */
    private int minAttendees;
    /**
     * Maximum number of attendees of meetup.
     */
    private int maxAttendees;
    /**
     * Description of meetup.
     */
    private String description;
    /**
     * Topics of meetup.
     */
    private List<Topic> topics;

}
