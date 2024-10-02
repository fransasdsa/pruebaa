package com.upu.msanalyticsservice.consumer;

import com.upu.msanalyticsservice.model.AnalyticsData;
import com.upu.msanalyticsservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component
public class AnalyticsConsumer {

    @Autowired
    private AnalyticsService analyticsService;

    @StreamListener("input")
    public void handleAnalyticsEvent(String eventPayload) {
        AnalyticsData data = new AnalyticsData();
        data.setEventType("EVENT_TYPE"); // Extraer del payload si es posible
        data.setPayload(eventPayload);
        data.setTimestamp(LocalDateTime.now());
        analyticsService.saveAnalyticsData(data).subscribe();
    }
}
