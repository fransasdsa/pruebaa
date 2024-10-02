package com.upu.msanalyticsservice.repository;

import com.upu.msanalyticsservice.model.AnalyticsData;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import java.time.LocalDateTime;
import java.util.UUID;

public interface AnalyticsRepository extends ReactiveCrudRepository<AnalyticsData, UUID> {
    Flux<AnalyticsData> findByEventType(String eventType);
    Flux<AnalyticsData> findByTimestampBetween(LocalDateTime start, LocalDateTime end);
}
