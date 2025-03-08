package com.microservice.shoppingcart.infrastructure.jpa.repository;

import com.microservice.shoppingcart.infrastructure.jpa.entity.ShoppingCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCartEntity, Long> {
    List<ShoppingCartEntity> findAllByUserId(Long userId);
}
