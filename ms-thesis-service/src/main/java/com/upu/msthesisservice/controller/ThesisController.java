// src/main/java/com/upu/msthesisservice/controller/ThesisController.java
package com.upu.msthesisservice.controller;

import com.upu.msthesisservice.model.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/theses")
public class ThesisController {

    @Autowired
    private ThesisService thesisService;

    @PreAuthorize("hasRole('STUDENT') or hasRole('ADVISOR')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Thesis> createThesis(@Valid @RequestBody Thesis thesis) {
        return thesisService.createThesis(thesis);
    }

    @PreAuthorize("hasRole('STUDENT') or hasRole('ADVISOR') or hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Mono<Thesis> getThesisById(@PathVariable UUID id) {
        return thesisService.getThesisById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public Flux<Thesis> getAllTheses() {
        return thesisService.getAllTheses();
    }

    @PreAuthorize("hasRole('ADVISOR') or hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Mono<Thesis> updateThesis(@PathVariable UUID id, @Valid @RequestBody Thesis thesis) {
        return thesisService.updateThesis(id, thesis);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteThesis(@PathVariable UUID id) {
        return thesisService.deleteThesis(id);
    }
}
