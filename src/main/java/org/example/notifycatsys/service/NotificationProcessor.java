package org.example.notifycatsys.service;

import org.example.notifycatsys.entity.Notification;
import org.example.notifycatsys.entity.NotificationStatus;
import org.example.notifycatsys.repository.NotificationRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;

@Service
public class NotificationProcessor {

    private static final Logger log = LoggerFactory.getLogger(NotificationProcessor.class);
    private final EmailService emailService;
    private final NotificationRepository notificationRepository;
    private final ThreadPoolTaskExecutor notificationExecutor;

    public NotificationProcessor(EmailService emailService, NotificationRepository notificationRepository, ThreadPoolTaskExecutor notificationExecutor) {
        this.emailService = emailService;
        this.notificationRepository = notificationRepository;
        this.notificationExecutor = notificationExecutor;
    }

    @Scheduled(fixedDelay = 5000)
    public void processPendingNotifications() {
        List<Notification> nots = notificationRepository.findByStatus(NotificationStatus.PENDING);

        for (Notification notification : nots) {
            notification.setStatus(NotificationStatus.SENDING);
            notificationRepository.save(notification);
            try{
                notificationExecutor.execute(() -> processNotification(notification));
            } catch (RejectedExecutionException e) {
                notification.setStatus(NotificationStatus.PENDING);
                notificationRepository.save(notification);

            }

        }
    }

    private void processNotification(Notification notification){

        try {
            emailService.sendEmail(notification);

            notification.setStatus(NotificationStatus.SENT);
            notification.setSentAt(LocalDateTime.now());

            notificationRepository.save(notification);

        } catch (Exception e) {
            notification.setStatus(NotificationStatus.FAILED);
            log.error("Failed to send notification id={}", notification.getId(), e);
            notificationRepository.save(notification);
        }
    }
}
