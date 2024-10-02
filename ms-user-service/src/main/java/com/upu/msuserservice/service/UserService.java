// src/main/java/com/upu/msuserservice/service/UserService.java
package com.upu.msuserservice.service;

import com.upu.msuserservice.model.User;
import com.upu.msuserservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Mono<User> createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    public Mono<User> getUserById(UUID id) {
        return userRepository.findById(id);
    }

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> updateUser(UUID id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setUsername(user.getUsername());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setFirstName(user.getFirstName());
                    existingUser.setLastName(user.getLastName());
                    return userRepository.save(existingUser);
                });
    }

    public Mono<Void> deleteUser(UUID id) {
        return userRepository.deleteById(id);
    }
}
