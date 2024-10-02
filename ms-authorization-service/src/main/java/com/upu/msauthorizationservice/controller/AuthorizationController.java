// src/main/java/com/upu/msauthorizationservice/controller/AuthorizationController.java
package com.upu.msauthorizationservice.controller;

import com.upu.msauthorizationservice.model.Permission;
import com.upu.msauthorizationservice.model.Role;
import com.upu.msauthorizationservice.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @PostMapping("/roles")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        Role savedRole = authorizationService.createRole(role);
        return ResponseEntity.ok(savedRole);
    }

    @PostMapping("/permissions")
    public ResponseEntity<Permission> createPermission(@RequestBody Permission permission) {
        Permission savedPermission = authorizationService.createPermission(permission);
        return ResponseEntity.ok(savedPermission);
    }

    @PostMapping("/roles/{roleId}/permissions/{permissionId}")
    public ResponseEntity<Role> assignPermissionToRole(
            @PathVariable UUID roleId,
            @PathVariable UUID permissionId) {
        Role updatedRole = authorizationService.assignPermissionToRole(roleId, permissionId);
        return ResponseEntity.ok(updatedRole);
    }
}
