// src/main/java/com/upu/msauthorizationservice/repository/PermissionRepository.java
package com.upu.msauthorizationservice.repository;

import com.upu.msauthorizationservice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PermissionRepository extends JpaRepository<Permission, UUID> {
    Optional<Permission> findByName(String name);
}
