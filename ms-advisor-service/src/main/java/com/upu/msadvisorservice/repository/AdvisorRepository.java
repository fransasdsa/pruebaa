// src/main/java/com/upu/msadvisorservice/repository/AdvisorRepository.java
package com.upu.msadvisorservice.repository;

import com.upu.msadvisorservice.model.Advisor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface AdvisorRepository extends ReactiveCrudRepository<Advisor, UUID> {
    Flux<Advisor> findByAvailableTrue();
    Flux<Advisor> findByDepartment(String department);
}
