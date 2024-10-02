// src/main/java/com/upu/msthesisservice/service/ThesisService.java
package com.upu.msthesisservice.service;

import com.upu.msthesisservice.model.Thesis;
import com.upu.msthesisservice.repository.ThesisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ThesisService {

    private final ThesisRepository thesisRepository;
    private final StudentClient studentClient;
    private final AdvisorClient advisorClient;
    private final ResearchLineClient researchLineClient;
    private final DocumentClient documentClient;
    private final StreamBridge streamBridge;

    // Inyección de dependencias mediante constructor
    @Autowired
    public ThesisService(ThesisRepository thesisRepository,
                         StudentClient studentClient,
                         AdvisorClient advisorClient,
                         ResearchLineClient researchLineClient,
                         DocumentClient documentClient,
                         StreamBridge streamBridge) {
        this.thesisRepository = thesisRepository;
        this.studentClient = studentClient;
        this.advisorClient = advisorClient;
        this.researchLineClient = researchLineClient;
        this.documentClient = documentClient;
        this.streamBridge = streamBridge;
    }

    public Mono<Thesis> createThesis(Thesis thesis) {
        return studentClient.getStudentById(thesis.getStudentId())
                .switchIfEmpty(Mono.error(new RuntimeException("El estudiante no existe.")))
                .flatMap(student -> advisorClient.getAdvisorById(thesis.getAdvisorId())
                        .switchIfEmpty(Mono.error(new RuntimeException("El asesor no existe."))))
                .flatMap(advisor -> {
                    if (thesis.getResearchLineId() != null) {
                        return researchLineClient.getResearchLineById(thesis.getResearchLineId())
                                .switchIfEmpty(Mono.error(new RuntimeException("La línea de investigación no existe.")));
                    }
                    return Mono.empty();
                })
                .then(Mono.defer(() -> {
                    thesis.setId(UUID.randomUUID());
                    thesis.setCreatedAt(LocalDateTime.now());
                    thesis.setUpdatedAt(LocalDateTime.now());
                    return thesisRepository.save(thesis);
                }))
                .flatMap(savedThesis -> {
                    // Subir documento inicial (si aplica)
                    // Aquí no se implementa la subida de archivos, pero podrías añadirla según tus necesidades
                    return Mono.just(savedThesis);
                })
                .doOnSuccess(savedThesis -> {
                    // Publicar evento para notificaciones
                    ThesisEvent event = new ThesisEvent();
                    event.setEventType("THESIS_CREATED");
                    event.setThesisId(savedThesis.getId());
                    event.setStudentId(savedThesis.getStudentId());
                    event.setAdvisorId(savedThesis.getAdvisorId());
                    event.setTitle(savedThesis.getTitle());
                    streamBridge.send("thesisEvents-out-0", event);
                });
    }

    public Mono<Thesis> getThesisById(UUID id) {
        return thesisRepository.findById(id);
    }

    public Flux<Thesis> getThesesByStudentId(UUID studentId) {
        return thesisRepository.findByStudentId(studentId);
    }

    public Flux<Thesis> getThesesByAdvisorId(UUID advisorId) {
        return thesisRepository.findByAdvisorId(advisorId);
    }

    public Flux<Thesis> getThesesByResearchLineId(UUID researchLineId) {
        return thesisRepository.findByResearchLineId(researchLineId);
    }

    public Flux<Thesis> getAllTheses() {
        return thesisRepository.findAll();
    }

    public Mono<Thesis> updateThesis(UUID id, Thesis thesis) {
        return thesisRepository.findById(id)
                .flatMap(existingThesis -> {
                    existingThesis.setTitle(thesis.getTitle());
                    existingThesis.setDescription(thesis.getDescription());
                    existingThesis.setAdvisorId(thesis.getAdvisorId());
                    existingThesis.setResearchLineId(thesis.getResearchLineId());
                    existingThesis.setStatus(thesis.getStatus());
                    existingThesis.setUpdatedAt(LocalDateTime.now());
                    return thesisRepository.save(existingThesis);
                });
    }

    public Mono<Void> deleteThesis(UUID id) {
        return thesisRepository.deleteById(id);
    }

}
