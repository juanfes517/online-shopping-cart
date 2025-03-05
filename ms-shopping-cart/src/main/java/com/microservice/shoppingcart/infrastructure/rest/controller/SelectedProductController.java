package com.microservice.shoppingcart.infrastructure.rest.controller;

import com.microservice.shoppingcart.application.service.ProductService;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/selected-products")
@RequiredArgsConstructor
public class SelectedProductController {

    private final ProductService productService;

    @PatchMapping("{selected-products-id}/amount/{amount}")
    public ResponseEntity<SelectedProduct> changeAmount(
            @PathVariable(name = "selected-products-id") Long id, @PathVariable(name = "amount") Integer amount){
        return ResponseEntity.ok(productService.changeAmount(id, amount));
    }
}
