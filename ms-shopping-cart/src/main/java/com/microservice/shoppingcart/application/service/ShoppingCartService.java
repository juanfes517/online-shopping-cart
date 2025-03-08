package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.port.input.ShoppingCartServicePort;
import com.microservice.shoppingcart.application.port.input.UserServicePort;
import com.microservice.shoppingcart.application.port.output.ShoppingCartPersistencePort;
import com.microservice.shoppingcart.domain.model.ShoppingCart;
import com.microservice.shoppingcart.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class ShoppingCartService implements ShoppingCartServicePort {

    private final ShoppingCartPersistencePort shoppingCartPersistencePort;
    private final UserServicePort userService;

    @Override
    public List<ShoppingCart> getShoppingCarts(Long userId) {
        System.out.println("HOlaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        User user = userService.getUserById(userId);
        System.out.println(user.getId() + "---------------------" +user.getUsername());

        return shoppingCartPersistencePort.findAllByUser(user);
    }

    @Override
    public ShoppingCart getShoppingCart(Long shoppingCartId) {
        return shoppingCartPersistencePort.findById(shoppingCartId)
                .orElseThrow(() -> new NotFoundException("Shopping cart not found"));
    }

    @Override
    public ShoppingCart createShoppingCart(Long userId, String name, String description) {
        User user = userService.getUserById(userId);

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .name(name)
                .description(description)
                .createdAt(LocalDateTime.now())
                .user(user)
                .totalPrice(0)
                .build();

        return shoppingCartPersistencePort.save(shoppingCart);
    }
}
