package com.microservice.shoppingcart.infrastructure.jpa.repository;

import com.microservice.shoppingcart.infrastructure.jpa.entity.SelectedProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedProductRepository extends JpaRepository<SelectedProductEntity, Long> {
}
