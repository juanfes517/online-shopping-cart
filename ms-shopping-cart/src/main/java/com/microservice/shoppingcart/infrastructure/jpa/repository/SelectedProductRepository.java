package com.microservice.shoppingcart.infrastructure.jpa.repository;

import com.microservice.shoppingcart.domain.model.SelectedProduct;
import com.microservice.shoppingcart.infrastructure.jpa.entity.SelectedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SelectedProductRepository extends JpaRepository<SelectedProductEntity, Long> {
    Optional<SelectedProduct> findByProductCode(String productCode);
}
