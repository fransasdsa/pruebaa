// src/main/java/com/upu/msadvisorservice/model/Advisor.java
package com.upu.msadvisorservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("advisors")
public class Advisor {
    @Id
    private UUID id;
    private UUID userId;
    private String specialization;
    private String department;
    private boolean available;
}
