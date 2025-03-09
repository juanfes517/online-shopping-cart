package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.Role;

import java.util.List;

public interface RolePersistencePort {
    List<Role> findAll();
    Role save(Role role);
    void deleteById(Long id);
    Role findById(Long id);
}
