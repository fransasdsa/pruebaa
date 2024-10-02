// src/main/java/com/upu/msstudentservice/model/Student.java
package com.upu.msstudentservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("students")
public class Student {
    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String identificationNumber; // Número de identificación (DNI, pasaporte, etc.)
    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;
    private String program; // Programa académico en el que está inscrito
    private LocalDate enrollmentDate; // Fecha de inscripción
    private String status; // e.g., "Active", "Graduated", "Inactive"
}
