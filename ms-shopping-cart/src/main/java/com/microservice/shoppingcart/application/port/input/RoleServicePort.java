package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.response.RoleResponseDTO;

import java.util.List;

public interface RoleServicePort {
    List<RoleResponseDTO> getAllRoles();
    RoleResponseDTO createRol(String roleName);
    RoleResponseDTO deleteRol(Long id);
}
