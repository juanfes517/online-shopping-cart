package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.SelectedProduct;

import java.util.Optional;

public interface SelectedProductPersistencePort {
    Optional<SelectedProduct> findById(Long id);
    SelectedProduct save(SelectedProduct selectedProduct);
}
