package org.example.notifycatsys.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.example.notifycatsys.entity.Notification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    private final JavaMailSender mailSender;
    private final String from;
    public EmailService(JavaMailSender mailSender, @Value("${app.mail.from}") String from) {
        this.mailSender = mailSender;
        this.from = from;
    }

    public void sendEmail(Notification notification){
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(notification.getEmail());
            helper.setSubject(notification.getSubject());
            helper.setText(notification.getMessage());
            helper.setFrom(from);

            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при отправке письма",e);
        }
    }

}
