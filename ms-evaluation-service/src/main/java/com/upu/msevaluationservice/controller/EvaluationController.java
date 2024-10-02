// src/main/java/com/upu/msevaluationservice/controller/EvaluationController.java
package com.upu.msevaluationservice.controller;

import com.upu.msevaluationservice.model.Evaluation;
import com.upu.msevaluationservice.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/evaluations")
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Evaluation> createEvaluation(@RequestBody Evaluation evaluation) {
        return evaluationService.createEvaluation(evaluation);
    }

    @GetMapping("/{id}")
    public Mono<Evaluation> getEvaluationById(@PathVariable UUID id) {
        return evaluationService.getEvaluationById(id);
    }

    @GetMapping("/thesis/{thesisId}")
    public Flux<Evaluation> getEvaluationsByThesisId(@PathVariable UUID thesisId) {
        return evaluationService.getEvaluationsByThesisId(thesisId);
    }

    @GetMapping("/evaluator/{evaluatorId}")
    public Flux<Evaluation> getEvaluationsByEvaluatorId(@PathVariable UUID evaluatorId) {
        return evaluationService.getEvaluationsByEvaluatorId(evaluatorId);
    }

    @GetMapping
    public Flux<Evaluation> getAllEvaluations() {
        return evaluationService.getAllEvaluations();
    }

    @PutMapping("/{id}")
    public Mono<Evaluation> updateEvaluation(@PathVariable UUID id, @RequestBody Evaluation evaluation) {
        return evaluationService.updateEvaluation(id, evaluation);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteEvaluation(@PathVariable UUID id) {
        return evaluationService.deleteEvaluation(id);
    }
}
