// src/main/java/com/upu/msauthorizationservice/repository/RoleRepository.java
package com.upu.msauthorizationservice.repository;

import com.upu.msauthorizationservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(String name);
}
