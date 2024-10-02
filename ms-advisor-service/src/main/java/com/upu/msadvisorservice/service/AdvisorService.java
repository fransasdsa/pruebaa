// src/main/java/com/upu/msadvisorservice/service/AdvisorService.java
package com.upu.msadvisorservice.service;

import com.upu.msadvisorservice.model.Advisor;
import com.upu.msadvisorservice.repository.AdvisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AdvisorService {

    @Autowired
    private AdvisorRepository advisorRepository;

    public Mono<Advisor> createAdvisor(Advisor advisor) {
        advisor.setId(UUID.randomUUID());
        return advisorRepository.save(advisor);
    }

    public Mono<Advisor> getAdvisorById(UUID id) {
        return advisorRepository.findById(id);
    }

    public Flux<Advisor> getAllAdvisors() {
        return advisorRepository.findAll();
    }

    public Flux<Advisor> getAvailableAdvisors() {
        return advisorRepository.findByAvailableTrue();
    }

    public Flux<Advisor> getAdvisorsByDepartment(String department) {
        return advisorRepository.findByDepartment(department);
    }

    public Mono<Advisor> updateAdvisor(UUID id, Advisor advisor) {
        return advisorRepository.findById(id)
                .flatMap(existingAdvisor -> {
                    existingAdvisor.setSpecialization(advisor.getSpecialization());
                    existingAdvisor.setDepartment(advisor.getDepartment());
                    existingAdvisor.setAvailable(advisor.isAvailable());
                    return advisorRepository.save(existingAdvisor);
                });
    }

    public Mono<Void> deleteAdvisor(UUID id) {
        return advisorRepository.deleteById(id);
    }
}
