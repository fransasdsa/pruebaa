// src/main/java/com/upu/msuserservice/controller/UserController.java
package com.upu.msuserservice.controller;

import com.upu.msuserservice.model.User;
import com.upu.msuserservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<User> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/{id}")
    public Mono<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public Flux<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{id}")
    public Mono<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteUser(@PathVariable UUID id) {
        return userService.deleteUser(id);
    }
}
