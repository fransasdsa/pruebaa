// src/main/java/com/upu/msthesisservice/dto/StudentDto.java
package com.upu.msthesisservice.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class StudentDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    // Otros campos relevantes
}
