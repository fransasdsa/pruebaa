// src/main/java/com/upu/msdocumentservice/service/DocumentService.java
package com.upu.msdocumentservice.service;

import com.upu.msdocumentservice.model.Document;
import com.upu.msdocumentservice.repository.DocumentContentStore;
import com.upu.msdocumentservice.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.UUID;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private DocumentContentStore contentStore;

    public Mono<Document> uploadDocument(MultipartFile file, UUID ownerId, String ownerType, String relatedEntityId) {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setName(file.getOriginalFilename());
        document.setContentType(file.getContentType());
        document.setOwnerId(ownerId);
        document.setOwnerType(ownerType);
        document.setRelatedEntityId(relatedEntityId);

        return documentRepository.save(document)
                .flatMap(savedDocument -> {
                    try {
                        contentStore.setContent(savedDocument, file.getInputStream());
                        return documentRepository.save(savedDocument);
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
                });
    }

    public Mono<Document> getDocumentById(UUID id) {
        return documentRepository.findById(id);
    }

    public Flux<Document> getDocumentsByOwnerId(UUID ownerId) {
        return documentRepository.findByOwnerId(ownerId);
    }

    public Mono<Void> deleteDocument(UUID id) {
        return documentRepository.findById(id)
                .flatMap(document -> {
                    contentStore.unsetContent(document);
                    return documentRepository.delete(document);
                });
    }
}
