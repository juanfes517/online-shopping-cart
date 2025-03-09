package com.microservice.inventory.application.port.output;

import com.microservice.inventory.domain.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductPersisetencePort {
    List<Product> findAll();
    Optional<Product> findByProductCode(String productCode);
    Product save(Product product);
}
