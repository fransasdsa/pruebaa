// src/main/java/com/upu/msadvisorservice/controller/AdvisorController.java
package com.upu.msadvisorservice.controller;

import com.upu.msadvisorservice.model.Advisor;
import com.upu.msadvisorservice.service.AdvisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/advisors")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Advisor> createAdvisor(@RequestBody Advisor advisor) {
        return advisorService.createAdvisor(advisor);
    }

    @GetMapping("/{id}")
    public Mono<Advisor> getAdvisorById(@PathVariable UUID id) {
        return advisorService.getAdvisorById(id);
    }

    @GetMapping
    public Flux<Advisor> getAllAdvisors() {
        return advisorService.getAllAdvisors();
    }

    @GetMapping("/available")
    public Flux<Advisor> getAvailableAdvisors() {
        return advisorService.getAvailableAdvisors();
    }

    @GetMapping("/department/{department}")
    public Flux<Advisor> getAdvisorsByDepartment(@PathVariable String department) {
        return advisorService.getAdvisorsByDepartment(department);
    }

    @PutMapping("/{id}")
    public Mono<Advisor> updateAdvisor(@PathVariable UUID id, @RequestBody Advisor advisor) {
        return advisorService.updateAdvisor(id, advisor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAdvisor(@PathVariable UUID id) {
        return advisorService.deleteAdvisor(id);
    }
}
