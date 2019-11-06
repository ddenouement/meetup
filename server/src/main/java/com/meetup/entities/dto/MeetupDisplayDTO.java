package com.meetup.entities.dto;

import com.meetup.entities.Language;
import com.meetup.entities.Topic;
import com.meetup.utils.MeetupState;
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
@NoArgsConstructor
@Data
@Builder
public class MeetupDisplayDTO {

    /**
     * ID of meetup.
     */
    private int id;
    /**
     * Speaker credentials of meetup.
     */
    private SimpleUserDTO speaker;
    /**
     * Language of meetup.
     */
    private Language language;
    /**
     * Topic of meetup.
     */
    private Topic topic;
    /**
     * State of meetup.
     */
    private MeetupState state;
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
