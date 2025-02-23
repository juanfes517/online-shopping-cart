package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.Role;

import java.util.List;

public interface RolePersistencePort {
    List<Role> findAll();
    Role save(Role role);
    Role deleteById(Long id);
}
