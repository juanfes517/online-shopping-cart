package com.microservice.inventory.infrastructure.jpa.repository;

import com.microservice.inventory.domain.model.Product;
import com.microservice.inventory.infrastructure.jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
    Optional<Product> findByProductCode(String productCode);
}
