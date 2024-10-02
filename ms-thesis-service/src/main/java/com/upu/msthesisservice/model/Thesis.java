// src/main/java/com/upu/msthesisservice/model/Thesis.java
package com.upu.msthesisservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("theses")
public class Thesis {
    @Id
    private UUID id;

    @NotNull(message = "El ID del estudiante es obligatorio.")
    private UUID studentId;

    @NotNull(message = "El ID del asesor es obligatorio.")
    private UUID advisorId;

    @NotBlank(message = "El título es obligatorio.")
    private String title;

    @NotBlank(message = "La descripción es obligatoria.")
    private String description;

    private UUID researchLineId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
