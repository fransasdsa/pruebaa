// src/main/java/com/upu/msdocumentservice/repository/DocumentContentStore.java
package com.upu.msdocumentservice.repository;

import com.upu.msdocumentservice.model.Document;
import org.springframework.content.commons.repository.ContentStore;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public interface DocumentContentStore extends ContentStore<Document, UUID> {
}
