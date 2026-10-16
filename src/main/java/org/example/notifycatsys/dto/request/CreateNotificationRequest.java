package org.example.notifycatsys.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class CreateNotificationRequest {
    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String subject;

    @NotBlank
    private String message;

    public CreateNotificationRequest() {
    }

    public CreateNotificationRequest(String email, String subject, String message) {
        this.email = email;
        this.subject = subject;
        this.message = message;
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
}
