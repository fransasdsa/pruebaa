// src/main/java/com/upu/msevaluationservice/service/EvaluationService.java
package com.upu.msevaluationservice.service;

import com.upu.msevaluationservice.model.Evaluation;
import com.upu.msevaluationservice.repository.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public Mono<Evaluation> createEvaluation(Evaluation evaluation) {
        evaluation.setId(UUID.randomUUID());
        evaluation.setEvaluatedAt(LocalDateTime.now());
        return evaluationRepository.save(evaluation);
    }

    public Mono<Evaluation> getEvaluationById(UUID id) {
        return evaluationRepository.findById(id);
    }

    public Flux<Evaluation> getEvaluationsByThesisId(UUID thesisId) {
        return evaluationRepository.findByThesisId(thesisId);
    }

    public Flux<Evaluation> getEvaluationsByEvaluatorId(UUID evaluatorId) {
        return evaluationRepository.findByEvaluatorId(evaluatorId);
    }

    public Flux<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Mono<Evaluation> updateEvaluation(UUID id, Evaluation evaluation) {
        return evaluationRepository.findById(id)
                .flatMap(existingEvaluation -> {
                    existingEvaluation.setScore(evaluation.getScore());
                    existingEvaluation.setComments(evaluation.getComments());
                    existingEvaluation.setEvaluatedAt(LocalDateTime.now());
                    return evaluationRepository.save(existingEvaluation);
                });
    }

    public Mono<Void> deleteEvaluation(UUID id) {
        return evaluationRepository.deleteById(id);
    }
}
