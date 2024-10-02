package com.upu.msanalyticsservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Table("analytics_data")
public class AnalyticsData {
    @Id
    private UUID id;
    private String eventType;
    private String payload;
    private LocalDateTime timestamp;
}
