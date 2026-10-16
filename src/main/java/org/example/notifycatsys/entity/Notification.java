package org.example.notifycatsys.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name="subject")
    private String subject;

    @Column(name="message")
    private String message;

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private NotificationStatus status;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="sent_at")
    private LocalDateTime sentAt;

    public Notification(){

    }
    public Notification(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
        this.status = NotificationStatus.PENDING;
    }

    public Long getId() {
        return id;
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

    @PrePersist
    private void init(){
        createdAt = LocalDateTime.now();
    }
}
