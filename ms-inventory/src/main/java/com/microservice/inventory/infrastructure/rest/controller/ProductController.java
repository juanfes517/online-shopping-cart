package com.microservice.inventory.infrastructure.rest.controller;

import com.microservice.inventory.application.dto.response.ProductResponseDTO;
import com.microservice.inventory.application.port.input.ProductServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServicePort productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{product-code}/stock")
    public ResponseEntity<Boolean> verifyStockOfProduct(
            @PathVariable("product-code") String productCode,
            @RequestParam int quantity) {

        return ResponseEntity.ok(productService.verifyStockOfProduct(productCode, quantity));
    }
}
