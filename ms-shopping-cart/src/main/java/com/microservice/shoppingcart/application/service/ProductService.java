package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.response.ProductResponseDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.port.input.ProductServicePort;
import com.microservice.shoppingcart.application.port.output.SelectedProductPersistencePort;
import com.microservice.shoppingcart.domain.model.SelectedProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final SelectedProductPersistencePort selectedProductPersistencePort;

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        // TODO: Integrate with ms-inventory
        return List.of();
    }

    @Override
    public SelectedProduct changeAmount(Long productId, int amount) {
        SelectedProduct selectedProduct = selectedProductPersistencePort.findById(productId)
                .orElseThrow(() -> new NotFoundException("The product was not found"));

        selectedProduct.setAmount(amount);

        return selectedProductPersistencePort.save(selectedProduct);
    }
}
