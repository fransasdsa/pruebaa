package com.upu.msschedulerservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("events")
public class Event {
    @Id
    private UUID id;
    private String title;
    private String description;
    private UUID organizerId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String location;
}
