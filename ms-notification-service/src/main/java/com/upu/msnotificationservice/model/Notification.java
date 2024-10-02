// src/main/java/com/upu/msnotificationservice/model/Notification.java
package com.upu.msnotificationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("notifications")
public class Notification {
    @Id
    private UUID id;
    private UUID userId;
    private String channel; // e.g., EMAIL, SMS, PUSH
    private String recipient;
    private String subject;
    private String message;
    private String status; // e.g., PENDING, SENT, FAILED
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
