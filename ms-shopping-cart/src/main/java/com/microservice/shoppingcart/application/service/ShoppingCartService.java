package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.request.ShoppingCartRequestDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.port.input.ShoppingCartServicePort;
import com.microservice.shoppingcart.application.port.input.UserServicePort;
import com.microservice.shoppingcart.application.port.output.ShoppingCartPersistencePort;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
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
        return shoppingCartPersistencePort.findAllByUserId(userId);
    }

    @Override
    public ShoppingCart getShoppingCart(Long shoppingCartId) {
        return shoppingCartPersistencePort.findById(shoppingCartId)
                .orElseThrow(() -> new NotFoundException("Shopping cart not found"));
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCartRequestDTO shoppingCartRequestDTO) {
        User user = userService.getUserById(shoppingCartRequestDTO.getUserId());

        ShoppingCart shoppingCart = ShoppingCart.builder()
                .name(shoppingCartRequestDTO.getName())
                .description(shoppingCartRequestDTO.getDescription())
                .createdAt(LocalDateTime.now())
                .user(user)
                .totalPrice(0)
                .build();

        return shoppingCartPersistencePort.save(shoppingCart);
    }

    @Override
    public ShoppingCart addProductToShoppingCart(SelectedProduct selectedProduct, Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        Set<SelectedProduct> selectedProducts = shoppingCart.getSelectedProducts();

        selectedProducts.add(selectedProduct);
        shoppingCart.setSelectedProducts(selectedProducts);
        shoppingCart.calculateTotalPrice();

        return shoppingCartPersistencePort.save(shoppingCart);
    }

    @Override
    public ShoppingCart removeProductFromShoppingCart(SelectedProduct selectedProduct, Long shoppingCartId) {
        ShoppingCart shoppingCart = getShoppingCart(shoppingCartId);
        Set<SelectedProduct> selectedProducts = shoppingCart.getSelectedProducts();

        selectedProducts.remove(selectedProduct);
        shoppingCart.setSelectedProducts(selectedProducts);
        shoppingCart.calculateTotalPrice();

        return shoppingCartPersistencePort.save(shoppingCart);
    }
}
