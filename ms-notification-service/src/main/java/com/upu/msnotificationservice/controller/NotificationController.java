// src/main/java/com/upu/msnotificationservice/controller/NotificationController.java
package com.upu.msnotificationservice.controller;

import com.upu.msnotificationservice.model.Notification;
import com.upu.msnotificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Notification> sendNotification(@RequestBody Notification notification) {
        return notificationService.sendNotification(notification);
    }

    @GetMapping("/user/{userId}")
    public Flux<Notification> getNotificationsByUserId(@PathVariable UUID userId) {
        return notificationService.getNotificationsByUserId(userId);
    }

    @GetMapping("/pending")
    public Flux<Notification> getPendingNotifications() {
        return notificationService.getPendingNotifications();
    }
}
