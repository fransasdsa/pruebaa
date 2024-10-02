// src/main/java/com/upu/msthesisservice/repository/ThesisRepository.java
package com.upu.msthesisservice.repository;

import com.upu.msthesisservice.model.Thesis;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ThesisRepository extends ReactiveCrudRepository<Thesis, UUID> {
    Flux<Thesis> findByStudentId(UUID studentId);
    Flux<Thesis> findByAdvisorId(UUID advisorId);
    Flux<Thesis> findByResearchLineId(UUID researchLineId);
}
