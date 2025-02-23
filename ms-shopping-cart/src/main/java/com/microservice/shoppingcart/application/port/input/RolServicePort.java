package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.response.RolResponseDTO;

import java.util.List;

public interface RolServicePort {
    List<RolResponseDTO> getAllRoles();
    RolResponseDTO createRol(String rolName);
    RolResponseDTO deleteRol(String rolName);
}
