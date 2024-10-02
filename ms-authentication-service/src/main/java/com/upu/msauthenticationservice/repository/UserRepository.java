// src/main/java/com/upu/msauthenticationservice/repository/UserRepository.java
package com.upu.msauthenticationservice.repository;

import com.upu.msauthenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}
