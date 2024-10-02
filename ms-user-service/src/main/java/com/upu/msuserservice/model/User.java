// src/main/java/com/upu/msuserservice/model/User.java
package com.upu.msuserservice.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

@Data
@Table("users")
public class User {
    @Id
    private UUID id;
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private boolean active;
}
