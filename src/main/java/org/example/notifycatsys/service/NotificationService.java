package org.example.notifycatsys.service;

import org.example.notifycatsys.dto.request.CreateNotificationRequest;
import org.example.notifycatsys.dto.response.NotificationResponse;
import org.example.notifycatsys.entity.Notification;
import org.example.notifycatsys.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public NotificationResponse createNotification(CreateNotificationRequest cnr){
        Notification notification = new Notification(cnr.getEmail(), cnr.getSubject(), cnr.getMessage());
        Notification saved = notificationRepository.save(notification);
        return toResponse(saved);
    }

    public List<NotificationResponse> getAllNotifications(){
        List<Notification> ntf = notificationRepository.findAll();
        List<NotificationResponse> rsp = new ArrayList<>();
        for (Notification not: ntf){
            rsp.add(toResponse(not));
        }
        return rsp;
    }

    public NotificationResponse getNotificationById(Long id){
        Optional<Notification> op = notificationRepository.findById(id);
        return op.map(this::toResponse).orElse(null);
    }

    public Boolean deleteNotificationById(Long id){
        if (notificationRepository.existsById(id)){
            notificationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private NotificationResponse toResponse(Notification not){
        return new NotificationResponse(
                not.getId(),
                not.getEmail(),
                not.getSubject(),
                not.getMessage(),
                not.getStatus(),
                not.getCreatedAt(),
                not.getSentAt()
        );
    }

}