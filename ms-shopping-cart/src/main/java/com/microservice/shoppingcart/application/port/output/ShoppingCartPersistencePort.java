package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartPersistencePort {
    List<ShoppingCart> findAllByUserId(Long userId);
    Optional<ShoppingCart> findById(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
}
