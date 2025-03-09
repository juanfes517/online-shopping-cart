package com.microservice.inventory.application.port.input;

import com.microservice.inventory.application.dto.request.OrderRequestDTO;
import com.microservice.inventory.domain.model.Product;

import java.util.List;

public interface ProductServicePort {
    List<ProductResponseDTO> findAllProducts();
    boolean verifyStockOfProduct(String productCode, int stock);
    void processOrder(OrderRequestDTO order);
}
