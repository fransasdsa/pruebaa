// src/main/java/com/upu/msdocumentservice/model/Document.java
package com.upu.msdocumentservice.model;

import lombok.Data;
import org.springframework.content.commons.annotations.ContentId;
import org.springframework.content.commons.annotations.ContentLength;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("documents")
public class Document {
    @Id
    private UUID id;
    private String name;
    private String contentType;
    private UUID ownerId; // Puede ser studentId o advisorId
    private String ownerType; // "STUDENT" o "ADVISOR"
    private String relatedEntityId; // ID de la tesis, evaluación, etc.

    @ContentId
    private UUID contentId;

    @ContentLength
    private long contentLength;
}
