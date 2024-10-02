// src/main/java/com/upu/msthesisservice/dto/ResearchLineDto.java
package com.upu.msthesisservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ResearchLineDto {
    private UUID id;
    private String name;
    private String description;
    // Otros campos relevantes
}
