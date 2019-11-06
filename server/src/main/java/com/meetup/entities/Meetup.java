package com.meetup.entities;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * Model class of Meetup.
 */
@AllArgsConstructor
@Data
@Builder
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
     * Topic ID of meetup.
     */
    private int topicId;
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
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime startDate;
    /**
     * Duration of meetup.
     */
    private Integer durationMinutes;
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

}
