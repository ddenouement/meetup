package com.meetup.utils;

import com.meetup.service.IMeetupService;
import com.meetup.service.INotificationService;
import com.meetup.utils.constants.NotificationConstants;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:cron.properties")
public class ScheduledTasks {

    /**
     * Meetup operations.
     */
    private IMeetupService meetupService;
    /**
     * Notification operations.
     */
    private INotificationService notificationService;

    /**
     * Initialize.
     *
     * @param meetupService meetup operations
     * @param notificationService notification operations
     */
    @Autowired
    public ScheduledTasks(final IMeetupService meetupService,
        final INotificationService notificationService) {
        this.meetupService = meetupService;
        this.notificationService = notificationService;
    }

    /**
     * Periodically check which meetups start soon, and notify their speakers.
     */
    @Scheduled(cron = "${hosted_meetup_cron}")
    public void hostedMeetupsStartSoon() {
        LocalDateTime startTime = LocalDateTime.now()
            .truncatedTo(ChronoUnit.MINUTES)
            .plusMinutes(NotificationConstants.MINUTES_BEFORE_HOSTED_MEETUP);
        meetupService.getMeetupsByStartTime(startTime)
            .forEach(m -> notificationService
                .sendHostedMeetupStartsSoonNotification(m));
    }

    /**
     * Periodically check which meetups start soon and notify their listeners.
     */
    @Scheduled(cron = "${joined_meetup_cron}")
    public void joinedMeetupsStartSoon() {
        LocalDateTime startTime = LocalDateTime.now()
            .truncatedTo(ChronoUnit.MINUTES)
            .plusMinutes(NotificationConstants.MINUTES_BEFORE_JOINED_MEETUP);
        meetupService.getMeetupsByStartTime(startTime)
            .forEach(m -> notificationService
                .sendJoinedMeetupStartsSoonNotifications(m));
    }

    /**
     * Periodically check which meetups haven't started and cancel them.
     */
    @Scheduled(cron = "${cancel_meetups_cron}")
    public void cancelOutdatedMeetups() {
        meetupService.cancelOutdatedMeetups();
    }
}
