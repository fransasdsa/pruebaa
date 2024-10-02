// src/main/java/com/upu/msnotificationservice/repository/NotificationRepository.java
package com.upu.msnotificationservice.repository;

import com.upu.msnotificationservice.model.Notification;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface NotificationRepository extends ReactiveCrudRepository<Notification, UUID> {
    Flux<Notification> findByUserId(UUID userId);
    Flux<Notification> findByStatus(String status);
}
