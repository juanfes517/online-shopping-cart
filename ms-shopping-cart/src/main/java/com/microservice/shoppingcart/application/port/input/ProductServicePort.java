package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.response.ProductResponseDTO;
import com.microservice.shoppingcart.domain.model.SelectedProduct;

import java.util.List;

public interface ProductServicePort {
    List<ProductResponseDTO> getAllProducts();
    SelectedProduct changeAmount(Long productId, Long amount);
}
