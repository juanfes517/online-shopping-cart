package com.microservice.shoppingcart.infrastructure.jpa.repository;

import com.microservice.shoppingcart.infrastructure.jpa.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
