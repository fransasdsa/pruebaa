// src/main/java/com/upu/msresearchlineservice/service/ResearchLineService.java
package com.upu.msresearchlineservice.service;

import com.upu.msresearchlineservice.model.ResearchLine;
import com.upu.msresearchlineservice.repository.ResearchLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ResearchLineService {

    @Autowired
    private ResearchLineRepository researchLineRepository;

    public Mono<ResearchLine> createResearchLine(ResearchLine researchLine) {
        researchLine.setId(UUID.randomUUID());
        return researchLineRepository.save(researchLine);
    }

    public Mono<ResearchLine> getResearchLineById(UUID id) {
        return researchLineRepository.findById(id);
    }

    public Flux<ResearchLine> getAllResearchLines() {
        return researchLineRepository.findAll();
    }

    public Flux<ResearchLine> getResearchLinesByDepartment(String department) {
        return researchLineRepository.findByDepartment(department);
    }

    public Flux<ResearchLine> getResearchLinesByProgram(String program) {
        return researchLineRepository.findByProgram(program);
    }

    public Flux<ResearchLine> getResearchLinesByAdvisorId(UUID advisorId) {
        return researchLineRepository.findByAdvisorId(advisorId);
    }

    public Mono<ResearchLine> updateResearchLine(UUID id, ResearchLine researchLine) {
        return researchLineRepository.findById(id)
                .flatMap(existingResearchLine -> {
                    existingResearchLine.setName(researchLine.getName());
                    existingResearchLine.setDescription(researchLine.getDescription());
                    existingResearchLine.setDepartment(researchLine.getDepartment());
                    existingResearchLine.setProgram(researchLine.getProgram());
                    existingResearchLine.setAdvisorId(researchLine.getAdvisorId());
                    return researchLineRepository.save(existingResearchLine);
                });
    }

    public Mono<Void> deleteResearchLine(UUID id) {
        return researchLineRepository.deleteById(id);
    }
}
