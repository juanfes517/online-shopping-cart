package com.microservice.inventory.infrastructure.jpa.adapter;

import com.microservice.inventory.application.port.output.ProductPersistencePort;
import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.infrastructure.jpa.entity.ProductEntity;
import com.microservice.inventory.infrastructure.jpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductJpaAdapter implements ProductPersistencePort {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(productEntity -> modelMapper.map(productEntity, Product.class))
                .toList();
    }

    @Override
    public Optional<Product> findByProductCode(String productCode) {
        return productRepository.findByProductCode(productCode)
                .map(productEntity -> modelMapper.map(productEntity, Product.class));
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productRepository.save(
                modelMapper.map(product, ProductEntity.class));

        return modelMapper.map(productEntity, Product.class);
    }
}
