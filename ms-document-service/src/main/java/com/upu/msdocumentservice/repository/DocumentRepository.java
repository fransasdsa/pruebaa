// src/main/java/com/upu/msdocumentservice/repository/DocumentRepository.java
package com.upu.msdocumentservice.repository;

import com.upu.msdocumentservice.model.Document;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;

public interface DocumentRepository extends ReactiveCrudRepository<Document, UUID> {
    Flux<Document> findByOwnerId(UUID ownerId);
    Flux<Document> findByRelatedEntityId(String relatedEntityId);
}
