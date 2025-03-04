package com.microservice.shoppingcart.infrastructure.rest.controller;

import com.microservice.shoppingcart.application.dto.response.RoleResponseDTO;
import com.microservice.shoppingcart.application.port.input.RoleServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleServicePort roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> findAllRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }

    @PostMapping("/{role-name}")
    public ResponseEntity<RoleResponseDTO> createRole(@PathVariable(name = "role-name") String roleName) {
        return ResponseEntity.ok(roleService.createRol(roleName));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RoleResponseDTO> deleteRole(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(roleService.deleteRol(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<RoleResponseDTO> findRoleById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(roleService.findRol(id));
    }
}
