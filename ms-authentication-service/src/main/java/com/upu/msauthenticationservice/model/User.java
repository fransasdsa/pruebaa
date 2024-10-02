// src/main/java/com/upu/msauthenticationservice/model/User.java
package com.upu.msauthenticationservice.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;
    private String username;
    private String password;
    private String role;
}
