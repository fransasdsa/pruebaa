// src/main/java/com/upu/msauthorizationservice/model/Permission.java
package com.upu.msauthorizationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "permissions")
public class Permission {

    @Id
    private UUID id;

    @Column(unique = true)
    private String name;

    private String description;
}
