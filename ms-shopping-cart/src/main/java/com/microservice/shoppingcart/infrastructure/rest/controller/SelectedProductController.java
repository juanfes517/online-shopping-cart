package com.microservice.shoppingcart.infrastructure.rest.controller;

import com.microservice.shoppingcart.application.dto.request.SelectedProductResquestDTO;
import com.microservice.shoppingcart.application.dto.response.SelectedProductResponseDTO;
import com.microservice.shoppingcart.application.dto.response.ShoppingCartResponseDTO;
import com.microservice.shoppingcart.application.service.ProductService;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.domain.model.ShoppingCart;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/selected-products")
@RequiredArgsConstructor
public class SelectedProductController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @PatchMapping("{product-code}/amount/{amount}")
    public ResponseEntity<SelectedProductResponseDTO> changeAmount(
            @PathVariable(name = "product-code") String productCode, @PathVariable(name = "amount") Integer amount){
        return ResponseEntity.ok(modelMapper.map(
                productService.changeAmount(productCode, amount), SelectedProductResponseDTO.class)
        );
    }

    @PostMapping("/shopping-carts/{shopping-car-id}")
    public ResponseEntity<ShoppingCartResponseDTO> addProductToShoppingCart(
            @RequestBody SelectedProductResquestDTO selectedProductRequest, @PathVariable("shopping-car-id") Long shoppingCarId){
        SelectedProduct selectedProduct = modelMapper.map(selectedProductRequest, SelectedProduct.class);

        return ResponseEntity.ok(modelMapper.map(
                productService.addProductToShoppingCart(shoppingCarId, selectedProduct), ShoppingCartResponseDTO.class)
        );
    }

    @DeleteMapping("/shopping-carts/{shopping-car-id}")
    public ResponseEntity<ShoppingCartResponseDTO> removeProductFromShoppingCart(
            @RequestBody SelectedProductResquestDTO selectedProductRequest, @PathVariable("shopping-car-id") Long shoppingCarId){
        SelectedProduct selectedProduct = modelMapper.map(selectedProductRequest, SelectedProduct.class);

        return ResponseEntity.ok(modelMapper.map(
                productService.removeProductFromShoppingCart(shoppingCarId, selectedProduct), ShoppingCartResponseDTO.class)
        );
    }
}
