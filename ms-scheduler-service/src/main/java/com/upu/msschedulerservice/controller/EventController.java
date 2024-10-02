package com.upu.msschedulerservice.controller;

import com.upu.msschedulerservice.model.Event;
import com.upu.msschedulerservice.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public Mono<Event> createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @GetMapping("/{id}")
    public Mono<Event> getEventById(@PathVariable UUID id) {
        return eventService.getEventById(id);
    }

    @GetMapping("/organizer/{organizerId}")
    public Flux<Event> getEventsByOrganizer(@PathVariable UUID organizerId) {
        return eventService.getEventsByOrganizer(organizerId);
    }

    @PutMapping("/{id}")
    public Mono<Event> updateEvent(@PathVariable UUID id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteEvent(@PathVariable UUID id) {
        return eventService.deleteEvent(id);
    }
}
