package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    List<User> findAll();
    User save(User user);
    void deleteById(User user);
}
