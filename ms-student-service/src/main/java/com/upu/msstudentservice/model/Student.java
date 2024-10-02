// src/main/java/com/upu/msstudentservice/model/Student.java
package com.upu.msstudentservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("students")
public class Student {
    @Id
    private UUID id;
    private UUID userId;
    private String enrollmentNumber;
    private String program;
    private String department;
    private String status;
}
