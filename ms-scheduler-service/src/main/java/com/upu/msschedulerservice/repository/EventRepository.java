package com.upu.msschedulerservice.repository;

import com.upu.msschedulerservice.model.Event;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface EventRepository extends ReactiveCrudRepository<Event, UUID> {
    Flux<Event> findByOrganizerId(UUID organizerId);
}
