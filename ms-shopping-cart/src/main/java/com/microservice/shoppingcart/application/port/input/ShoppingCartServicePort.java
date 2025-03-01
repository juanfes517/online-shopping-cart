package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.domain.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartServicePort {
    List<ShoppingCart> getShoppingCarts(Long userId);
    ShoppingCart getShoppingCart(Long shoppingCartId);
    ShoppingCart createShoppingCart(Long userId);
    ShoppingCart addProductToShoppingCart(SelectedProduct selectedProduct, Long shoppingCartId);
    ShoppingCart removeProductFromShoppingCart(SelectedProduct selectedProduct, Long shoppingCartId);
}
