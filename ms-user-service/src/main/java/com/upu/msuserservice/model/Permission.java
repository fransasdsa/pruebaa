// src/main/java/com/upu/msuserservice/model/Permission.java
package com.upu.msuserservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Data
@Table("permissions")
public class Permission {
    @Id
    private UUID id;
    private String name;
    private String description;
}
