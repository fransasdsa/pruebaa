// src/main/java/com/upu/msresearchlineservice/model/ResearchLine.java
package com.upu.msresearchlineservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("research_lines")
public class ResearchLine {
    @Id
    private UUID id;
    private String name;
    private String description;
    private String department;
    private String program;
    private UUID advisorId;
}
