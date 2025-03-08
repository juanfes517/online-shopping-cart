package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.ShoppingCart;
import com.microservice.shoppingcart.domain.model.User;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPersistencePort {
    List<ShoppingCart> findAllByUser(User user);
    Optional<ShoppingCart> findById(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
}
