package com.microservice.inventory.application.service;

import com.microservice.inventory.application.dto.request.OrderRequestDTO;
import com.microservice.inventory.application.dto.response.ProductResponseDTO;
import com.microservice.inventory.application.exception.ProductNotFoundException;
import com.microservice.inventory.application.port.input.ProductServicePort;
import com.microservice.inventory.application.port.output.ProductPersistencePort;
import com.microservice.inventory.domain.model.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductService implements ProductServicePort {

    private final ProductPersistencePort productPersisetence;
    private final ModelMapper modelMapper;

    @Override
    public List<ProductResponseDTO> findAllProducts() {
        return productPersisetence.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponseDTO.class))
                .toList();
    }

    @Override
    public boolean verifyStockOfProduct(String productCode, int stock) {
        Product product = productPersisetence.findByProductCode(productCode)
                .orElseThrow(() -> new ProductNotFoundException("Product was not found. Code: " + productCode));
        return product.canPurchase(stock);
    }

    @Override
    public void processOrder(OrderRequestDTO order) {
        order.getProducts()
                .forEach(selectedProduct -> {
                    Product product = productPersisetence.findByProductCode(selectedProduct.getProductCode())
                            .orElseThrow(() -> new ProductNotFoundException("Product was not found. Code: " + selectedProduct.getProductCode()));
                    product.substractStock(selectedProduct.getAmount());
                    productPersisetence.save(product);
                });
    }
}
