// src/main/java/com/upu/msauthorizationservice/service/AuthorizationService.java
package com.upu.msauthorizationservice.service;

import com.upu.msauthorizationservice.model.Permission;
import com.upu.msauthorizationservice.model.Role;
import com.upu.msauthorizationservice.repository.PermissionRepository;
import com.upu.msauthorizationservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorizationService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    public Role createRole(Role role) {
        role.setId(UUID.randomUUID());
        return roleRepository.save(role);
    }

    public Permission createPermission(Permission permission) {
        permission.setId(UUID.randomUUID());
        return permissionRepository.save(permission);
    }

    public Role assignPermissionToRole(UUID roleId, UUID permissionId) {
        Role role = roleRepository.findById(roleId).orElseThrow();
        Permission permission = permissionRepository.findById(permissionId).orElseThrow();

        role.getPermissions().add(permission);
        return roleRepository.save(role);
    }
}
