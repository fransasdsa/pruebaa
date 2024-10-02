// src/main/java/com/upu/msevaluationservice/repository/EvaluationRepository.java
package com.upu.msevaluationservice.repository;

import com.upu.msevaluationservice.model.Evaluation;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface EvaluationRepository extends ReactiveCrudRepository<Evaluation, UUID> {
    Flux<Evaluation> findByThesisId(UUID thesisId);
    Flux<Evaluation> findByEvaluatorId(UUID evaluatorId);
}
