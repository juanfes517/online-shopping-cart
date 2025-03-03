package com.microservice.shoppingcart.infrastructure.jpa.adapter;

import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.port.output.RolePersistencePort;
import com.microservice.shoppingcart.domain.model.Role;
import com.microservice.shoppingcart.infrastructure.jpa.entity.RoleEntity;
import com.microservice.shoppingcart.infrastructure.jpa.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RoleJpaAdapter implements RolePersistencePort {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll().stream()
                .map(roleEntity -> modelMapper.map(roleEntity, Role.class))
                .toList();
    }

    @Override
    public Role save(Role role) {
        RoleEntity roleEntity = roleRepository.save(
                modelMapper.map(role, RoleEntity.class));

        return modelMapper.map(roleEntity, Role.class);
    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role findById(Long id) {
        RoleEntity roleEntity = roleRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("The role was not found"));

        return modelMapper.map(roleEntity, Role.class);
    }
}
