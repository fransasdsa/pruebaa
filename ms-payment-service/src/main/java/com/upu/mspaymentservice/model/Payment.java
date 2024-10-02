// src/main/java/com/upu/mspaymentservice/model/Payment.java
package com.upu.mspaymentservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("payments")
public class Payment {
    @Id
    private UUID id;
    private UUID studentId;
    private String description;
    private Double amount;
    private String currency;
    private String status; // e.g., PENDING, COMPLETED, FAILED
    private String paymentMethod; // e.g., CARD, BANK_TRANSFER
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
