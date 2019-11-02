package com.meetup.entities;

import com.meetup.utils.NotificationType;
import java.time.LocalDateTime;
import lombok.Data;

@Data
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
    private int idUser;
    /**
     * Whether user have read this notification.
     */
    private Boolean read;
    /**
     * Type of notification.
     */
    private NotificationType type;
    /**
     * When was tjis notification created.
     */
    private LocalDateTime timeCreated;
}
