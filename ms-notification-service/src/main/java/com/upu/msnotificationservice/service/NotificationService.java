// src/main/java/com/upu/msnotificationservice/service/NotificationService.java
package com.upu.msnotificationservice.service;

import com.upu.msnotificationservice.model.Notification;
import com.upu.msnotificationservice.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private JavaMailSender mailSender;

    public Mono<Notification> sendNotification(Notification notification) {
        notification.setId(UUID.randomUUID());
        notification.setStatus("PENDING");
        notification.setCreatedAt(LocalDateTime.now());
        notification.setUpdatedAt(LocalDateTime.now());

        return notificationRepository.save(notification)
                .flatMap(savedNotification -> {
                    if ("EMAIL".equalsIgnoreCase(savedNotification.getChannel())) {
                        return sendEmailNotification(savedNotification);
                    }
                    // Implementar otros canales como SMS o PUSH
                    return Mono.just(savedNotification);
                });
    }

    private Mono<Notification> sendEmailNotification(Notification notification) {
        return Mono.fromRunnable(() -> {
            try {
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(notification.getRecipient());
                mailMessage.setSubject(notification.getSubject());
                mailMessage.setText(notification.getMessage());
                mailSender.send(mailMessage);
                notification.setStatus("SENT");
            } catch (Exception e) {
                notification.setStatus("FAILED");
            }
            notification.setUpdatedAt(LocalDateTime.now());
            notificationRepository.save(notification).subscribe();
        }).thenReturn(notification);
    }

    public Flux<Notification> getNotificationsByUserId(UUID userId) {
        return notificationRepository.findByUserId(userId);
    }

    public Flux<Notification> getPendingNotifications() {
        return notificationRepository.findByStatus("PENDING");
    }
}
