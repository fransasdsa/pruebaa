package com.upu.msschedulerservice.service;

import com.upu.msschedulerservice.model.Event;
import com.upu.msschedulerservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.UUID;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public Mono<Event> createEvent(Event event) {
        event.setId(UUID.randomUUID());
        return eventRepository.save(event);
    }

    public Mono<Event> getEventById(UUID id) {
        return eventRepository.findById(id);
    }

    public Flux<Event> getEventsByOrganizer(UUID organizerId) {
        return eventRepository.findByOrganizerId(organizerId);
    }

    public Mono<Event> updateEvent(UUID id, Event event) {
        return eventRepository.findById(id)
                .flatMap(existingEvent -> {
                    existingEvent.setTitle(event.getTitle());
                    existingEvent.setDescription(event.getDescription());
                    existingEvent.setStartTime(event.getStartTime());
                    existingEvent.setEndTime(event.getEndTime());
                    existingEvent.setLocation(event.getLocation());
                    return eventRepository.save(existingEvent);
                });
    }

    public Mono<Void> deleteEvent(UUID id) {
        return eventRepository.deleteById(id);
    }
}
