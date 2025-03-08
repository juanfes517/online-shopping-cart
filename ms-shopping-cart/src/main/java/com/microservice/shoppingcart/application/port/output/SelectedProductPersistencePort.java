package com.microservice.shoppingcart.application.port.output;

import com.microservice.shoppingcart.domain.model.SelectedProduct;

import java.util.Optional;

public interface SelectedProductPersistencePort {
    Optional<SelectedProduct> findByProductCode(String productCode);
    SelectedProduct save(SelectedProduct selectedProduct);
}
