package org.example.notifycatsys.controller;

import jakarta.validation.Valid;
import org.example.notifycatsys.dto.request.CreateNotificationRequest;
import org.example.notifycatsys.dto.response.NotificationResponse;
import org.example.notifycatsys.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }


    @PostMapping("/api/notifications")
    public ResponseEntity<NotificationResponse> createNotification(
            @Valid @RequestBody CreateNotificationRequest cnr){

        NotificationResponse response = notificationService.createNotification(cnr);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/api/notifications")
    public List<NotificationResponse> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("/api/notifications/{id}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long id){
        NotificationResponse resp = notificationService.getNotificationById(id);
        if (resp==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        else return ResponseEntity.status(HttpStatus.OK).body(resp);
    }
    @DeleteMapping("/api/notifications/{id}")
    public ResponseEntity<Void> deleteNotificationById(@PathVariable Long id){
        Boolean resp = notificationService.deleteNotificationById(id);
        if (!resp) return ResponseEntity.notFound().build();
        else return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
