// src/main/java/com/upu/msthesisservice/dto/AdvisorDto.java
package com.upu.msthesisservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AdvisorDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    // Otros campos relevantes
}
