// src/main/java/com/upu/msuserservice/repository/UserRepository.java
package com.upu.msuserservice.repository;

import com.upu.msuserservice.model.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import java.util.UUID;

public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
    Mono<User> findByUsername(String username);
}
