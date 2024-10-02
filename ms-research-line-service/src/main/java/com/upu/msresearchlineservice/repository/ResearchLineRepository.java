// src/main/java/com/upu/msresearchlineservice/repository/ResearchLineRepository.java
package com.upu.msresearchlineservice.repository;

import com.upu.msresearchlineservice.model.ResearchLine;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface ResearchLineRepository extends ReactiveCrudRepository<ResearchLine, UUID> {
    Flux<ResearchLine> findByDepartment(String department);
    Flux<ResearchLine> findByProgram(String program);
    Flux<ResearchLine> findByAdvisorId(UUID advisorId);
}
