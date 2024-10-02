// src/main/java/com/upu/msdocumentservice/controller/DocumentController.java
package com.upu.msdocumentservice.controller;

import com.upu.msdocumentservice.model.Document;
import com.upu.msdocumentservice.repository.DocumentContentStore;
import com.upu.msdocumentservice.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @Autowired
    private DocumentContentStore contentStore;

    @PostMapping("/upload")
    public Mono<Document> uploadDocument(
            @RequestPart("file") MultipartFile file,
            @RequestParam UUID ownerId,
            @RequestParam String ownerType,
            @RequestParam(required = false) String relatedEntityId) {

        return documentService.uploadDocument(file, ownerId, ownerType, relatedEntityId);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> getDocument(@PathVariable UUID id) {
        return documentService.getDocumentById(id)
                .flatMap(document -> {
                    InputStreamResource inputStreamResource = new InputStreamResource(contentStore.getContent(document));
                    return Mono.just(ResponseEntity.ok()
                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + document.getName() + "\"")
                            .contentType(MediaType.parseMediaType(document.getContentType()))
                            .body(inputStreamResource));
                });
    }

    @GetMapping("/owner/{ownerId}")
    public Flux<Document> getDocumentsByOwner(@PathVariable UUID ownerId) {
        return documentService.getDocumentsByOwnerId(ownerId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteDocument(@PathVariable UUID id) {
        return documentService.deleteDocument(id);
    }
}
