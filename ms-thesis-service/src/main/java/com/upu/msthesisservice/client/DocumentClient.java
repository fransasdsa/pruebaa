// src/main/java/com/upu/msthesisservice/client/DocumentClient.java
package com.upu.msthesisservice.client;

import com.upu.msthesisservice.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class DocumentClient {

    @Autowired
    private WebClient webClient;

    private final String BASE_URL = "http://localhost:8089"; // URL de ms-document-service

    public Mono<Document> uploadDocument(UUID ownerId, String ownerType, String relatedEntityId, String fileUrl) {
        Document document = new Document();
        document.setId(UUID.randomUUID());
        document.setName("Documento de Tesis"); // Esto debería ser dinámico según el archivo
        document.setContentType("application/pdf"); // Esto también debería ser dinámico
        document.setOwnerId(ownerId);
        document.setOwnerType(ownerType);
        document.setRelatedEntityId(relatedEntityId);
        document.setContentId(UUID.randomUUID());
        document.setContentLength(0); // Actualizar según el tamaño real del archivo

        return webClient.post()
                .uri("/documents/upload")
                .bodyValue(document)
                .retrieve()
                .bodyToMono(Document.class);
    }
}
