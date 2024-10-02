package com.upu.msanalyticsservice.service;

import com.upu.msanalyticsservice.model.AnalyticsData;
import com.upu.msanalyticsservice.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class AnalyticsService {

    @Autowired
    private AnalyticsRepository analyticsRepository;

    public Mono<AnalyticsData> saveAnalyticsData(AnalyticsData data) {
        data.setId(UUID.randomUUID());
        return analyticsRepository.save(data);
    }

    public Flux<AnalyticsData> getAnalyticsDataByEventType(String eventType) {
        return analyticsRepository.findByEventType(eventType);
    }

    public Flux<AnalyticsData> getAllAnalyticsData() {
        return analyticsRepository.findAll();
    }
}
