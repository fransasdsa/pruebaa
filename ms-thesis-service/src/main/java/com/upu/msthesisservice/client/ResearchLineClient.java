// src/main/java/com/upu/msthesisservice/client/ResearchLineClient.java
package com.upu.msthesisservice.client;

import com.upu.msthesisservice.dto.ResearchLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
public class ResearchLineClient {

    @Autowired
    private WebClient webClient;

    private final String BASE_URL = "http://localhost:8083"; // URL de ms-research-line-service

    public Mono<ResearchLineDto> getResearchLineById(UUID researchLineId) {
        return webClient.get()
                .uri(BASE_URL + "/research-lines/{id}", researchLineId)
                .retrieve()
                .bodyToMono(ResearchLineDto.class);
    }
}
