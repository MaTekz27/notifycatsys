package org.example.notifycatsys.repository;

import org.example.notifycatsys.entity.Notification;
import org.example.notifycatsys.entity.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByStatus(NotificationStatus status);
}