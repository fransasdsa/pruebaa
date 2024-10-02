// src/main/java/com/upu/msuserservice/repository/RoleRepository.java
package com.upu.msuserservice.repository;

import com.upu.msuserservice.model.Role;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import java.util.UUID;

public interface RoleRepository extends ReactiveCrudRepository<Role, UUID> {
}
