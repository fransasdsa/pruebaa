package com.upu.msanalyticsservice.controller;

import com.upu.msanalyticsservice.model.AnalyticsData;
import com.upu.msanalyticsservice.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping
    public Flux<AnalyticsData> getAllAnalyticsData() {
        return analyticsService.getAllAnalyticsData();
    }

    @GetMapping("/event/{eventType}")
    public Flux<AnalyticsData> getAnalyticsDataByEventType(@PathVariable String eventType) {
        return analyticsService.getAnalyticsDataByEventType(eventType);
    }

    @PostMapping
    public Mono<AnalyticsData> saveAnalyticsData(@RequestBody AnalyticsData data) {
        return analyticsService.saveAnalyticsData(data);
    }
}
