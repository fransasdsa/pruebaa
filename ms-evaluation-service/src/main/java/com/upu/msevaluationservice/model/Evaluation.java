// src/main/java/com/upu/msevaluationservice/model/Evaluation.java
package com.upu.msevaluationservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("evaluations")
public class Evaluation {
    @Id
    private UUID id;
    private UUID thesisId;
    private UUID evaluatorId;
    private Double score;
    private String comments;
    private LocalDateTime evaluatedAt;
}
