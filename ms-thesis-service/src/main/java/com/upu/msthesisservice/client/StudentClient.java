// src/main/java/com/upu/msthesisservice/client/StudentClient.java
package com.upu.msthesisservice.client;

import com.upu.msthesisservice.dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class StudentClient {

    @Autowired
    private WebClient webClient;

    private final String BASE_URL = "http://localhost:8081"; // URL de ms-student-service

    public Mono<StudentDto> getStudentById(UUID studentId) {
        return webClient.get()
                .uri(BASE_URL + "/students/{id}", studentId)
                .retrieve()
                .bodyToMono(StudentDto.class);
    }
}
