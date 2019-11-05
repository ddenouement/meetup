package com.meetup.utils;

import com.meetup.entities.Notification;
import com.meetup.entities.dto.NotificationDTO;

public final class NotificationDTOConverter {

    public static NotificationDTO convert(
        final Notification notification) {
        return NotificationDTO.builder()
            .id(notification.getId())
            .message(notification.getMessage())
            .idUser(notification.getIdUser())
            .read(notification.getRead())
            .type(notification.getType().name())
            .timeCreated(notification.getTimeCreated())
            .build();
    }

    // Forbidden to call.
    private NotificationDTOConverter() {
        throw new AssertionError();
    }
}
