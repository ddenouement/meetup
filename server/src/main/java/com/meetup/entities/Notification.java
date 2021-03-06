package com.meetup.entities;

import com.meetup.utils.NotificationType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {

    /**
     * Notification's ID in database.
     */
    private Integer id;
    /**
     * Text of the notification.
     */
    private String message;
    /**
     * Id of user who should receive this notification.
     */
    private Integer idUser;
    /**
     * Whether user have read this notification.
     */
    private Boolean read;
    /**
     * Type of notification.
     */
    private NotificationType type;
    /**
     * When was this notification created.
     */
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeCreated;

    public Notification(final String message, final NotificationType type) {
        this.message = message;
        this.read = false;
        this.type = type;
        this.timeCreated = LocalDateTime.now();
    }
}
