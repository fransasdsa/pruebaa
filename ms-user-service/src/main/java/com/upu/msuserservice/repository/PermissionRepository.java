// src/main/java/com/upu/msuserservice/repository/PermissionRepository.java
package com.upu.msuserservice.repository;

import com.upu.msuserservice.model.Permission;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import java.util.UUID;

public interface PermissionRepository extends ReactiveCrudRepository<Permission, UUID> {
}
