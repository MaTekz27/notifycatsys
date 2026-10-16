package org.example.notifycatsys.dto.response;

import org.example.notifycatsys.entity.NotificationStatus;

import java.time.LocalDateTime;

public class NotificationResponse {
    private Long id;
    private String email;
    private String subject;
    private String message;
    private NotificationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;

    public NotificationResponse() {
    }

    public NotificationResponse(Long id, String email, String subject, String message, NotificationStatus status, LocalDateTime createdAt, LocalDateTime sentAt) {
        this.id = id;
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
        this.sentAt = sentAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public void setSentAt(LocalDateTime sentAt) {
        this.sentAt = sentAt;
    }
}
