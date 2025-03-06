package com.microservice.shoppingcart.infrastructure.rest.controller;

import com.microservice.shoppingcart.application.dto.request.SelectedProductResquestDTO;
import com.microservice.shoppingcart.application.dto.request.ShoppingCartRequestDTO;
import com.microservice.shoppingcart.application.service.ShoppingCartService;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.domain.model.ShoppingCart;
import com.microservice.shoppingcart.application.dto.response.ShoppingCartResponseDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shopping-carts")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;
    private final ModelMapper modelMapper;

    @GetMapping("/{user-id}")
    public ResponseEntity<List<ShoppingCartResponseDTO>> getShoppingCarts(@PathVariable("user-id") Long userId) {
        List<ShoppingCartResponseDTO> shoppingCarts = shoppingCartService.getShoppingCarts(userId).stream()
                .map(shoppingCart -> modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class))
                .toList();

        return ResponseEntity.ok(shoppingCarts);
    }

    @GetMapping("{shopping-cart_id}")
    public ResponseEntity<ShoppingCartResponseDTO> getShoppingCart(@PathVariable("shopping-cart_id") Long shoppingCartId) {
        ShoppingCart shoppingCart = shoppingCartService.getShoppingCart(shoppingCartId);

        return ResponseEntity.ok(modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class));
    }

    @PostMapping
    public ResponseEntity<ShoppingCartResponseDTO> createShoppingCart(@RequestBody ShoppingCartRequestDTO request) {
        ShoppingCart shoppingCart = shoppingCartService
                .createShoppingCart(request.getUserId(), request.getName(), request.getDescription());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(modelMapper.map(shoppingCart, ShoppingCartResponseDTO.class));
    }

    @PostMapping("/{shopping-cart-id}/selected-products")
    public ResponseEntity<ShoppingCartResponseDTO> addProductToShoppingCart(
            @PathVariable("shopping-cart-id") Long shoppingCartId,@RequestBody SelectedProductResquestDTO selectedProductRequest) {
        SelectedProduct selectedProduct = modelMapper.map(selectedProductRequest, SelectedProduct.class);
        ShoppingCart shoppingCartUpdated = shoppingCartService.addProductToShoppingCart(selectedProduct, shoppingCartId);

        return ResponseEntity.ok(modelMapper.map(shoppingCartUpdated, ShoppingCartResponseDTO.class));
    }

    @DeleteMapping("/{shopping-cart-id}/selected-products")
    public ResponseEntity<ShoppingCartResponseDTO> removeProductFromShoppingCart(
            @PathVariable("shopping-cart-id") Long shoppingCartId,@RequestBody SelectedProductResquestDTO selectedProductRequest) {
        SelectedProduct selectedProduct = modelMapper.map(selectedProductRequest, SelectedProduct.class);
        ShoppingCart shoppingCartUpdated = shoppingCartService.removeProductFromShoppingCart(selectedProduct, shoppingCartId);

        return ResponseEntity.ok(modelMapper.map(shoppingCartUpdated, ShoppingCartResponseDTO.class));
    }
}
