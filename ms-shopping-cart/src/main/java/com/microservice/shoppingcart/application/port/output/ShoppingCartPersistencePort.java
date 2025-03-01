package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartPersistencePort {
    List<ShoppingCart> findAllByUserId(Long userId);
    ShoppingCart findById(Long id);
    ShoppingCart save(ShoppingCart shoppingCart);
}
