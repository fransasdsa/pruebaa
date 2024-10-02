// src/main/java/com/upu/msresearchlineservice/controller/ResearchLineController.java
package com.upu.msresearchlineservice.controller;

import com.upu.msresearchlineservice.model.ResearchLine;
import com.upu.msresearchlineservice.service.ResearchLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/research-lines")
public class ResearchLineController {

    @Autowired
    private ResearchLineService researchLineService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ResearchLine> createResearchLine(@RequestBody ResearchLine researchLine) {
        return researchLineService.createResearchLine(researchLine);
    }

    @GetMapping("/{id}")
    public Mono<ResearchLine> getResearchLineById(@PathVariable UUID id) {
        return researchLineService.getResearchLineById(id);
    }

    @GetMapping
    public Flux<ResearchLine> getAllResearchLines() {
        return researchLineService.getAllResearchLines();
    }

    @GetMapping("/department/{department}")
    public Flux<ResearchLine> getResearchLinesByDepartment(@PathVariable String department) {
        return researchLineService.getResearchLinesByDepartment(department);
    }

    @GetMapping("/program/{program}")
    public Flux<ResearchLine> getResearchLinesByProgram(@PathVariable String program) {
        return researchLineService.getResearchLinesByProgram(program);
    }

    @GetMapping("/advisor/{advisorId}")
    public Flux<ResearchLine> getResearchLinesByAdvisorId(@PathVariable UUID advisorId) {
        return researchLineService.getResearchLinesByAdvisorId(advisorId);
    }

    @PutMapping("/{id}")
    public Mono<ResearchLine> updateResearchLine(@PathVariable UUID id, @RequestBody ResearchLine researchLine) {
        return researchLineService.updateResearchLine(id, researchLine);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteResearchLine(@PathVariable UUID id) {
        return researchLineService.deleteResearchLine(id);
    }
}
