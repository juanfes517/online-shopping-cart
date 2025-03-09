package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.response.RoleResponseDTO;
import com.microservice.shoppingcart.application.port.input.RoleServicePort;
import com.microservice.shoppingcart.application.port.output.RolePersistencePort;
import com.microservice.shoppingcart.domain.model.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleService implements RoleServicePort {

    private final RolePersistencePort rolePersistencePort;
    private final ModelMapper modelMapper;

    @Override
    public List<RoleResponseDTO> getAllRoles() {
        return rolePersistencePort.findAll().stream()
                .map(role -> modelMapper.map(role, RoleResponseDTO.class))
                .toList();
    }

    @Override
    public RoleResponseDTO createRol(String roleName) {
        Role role = Role.builder()
                .roleName(roleName)
                .build();

        return modelMapper.map(
                rolePersistencePort.save(role), RoleResponseDTO.class);
    }

    @Override
    public RoleResponseDTO deleteRol(Long id) {
        RoleResponseDTO role = this.findRol(id);
        rolePersistencePort.deleteById(id);
        return role;
    }

    @Override
    public RoleResponseDTO findRol(Long id) {
        return modelMapper.map(
                rolePersistencePort.findById(id), RoleResponseDTO.class);
    }
}
