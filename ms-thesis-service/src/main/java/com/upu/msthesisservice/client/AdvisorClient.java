// src/main/java/com/upu/msthesisservice/client/AdvisorClient.java
package com.upu.msthesisservice.client;

import com.upu.msthesisservice.dto.AdvisorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class AdvisorClient {

    @Autowired
    private WebClient webClient;

    private final String BASE_URL = "http://localhost:8082"; // URL de ms-advisor-service

    public Mono<AdvisorDto> getAdvisorById(UUID advisorId) {
        return webClient.get()
                .uri(BASE_URL + "/advisors/{id}", advisorId)
                .retrieve()
                .bodyToMono(AdvisorDto.class);
    }
}
