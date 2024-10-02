// src/main/java/com/upu/msthesisservice/event/ThesisEvent.java
package com.upu.msthesisservice.event;

import lombok.Data;

import java.util.UUID;

@Data
public class ThesisEvent {
    private String eventType;
    private UUID thesisId;
    private UUID studentId;
    private UUID advisorId;
    private String title;
    // Otros campos relevantes
}
