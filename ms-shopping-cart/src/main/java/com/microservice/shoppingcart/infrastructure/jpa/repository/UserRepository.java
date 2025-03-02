package com.microservice.shoppingcart.infrastructure.jpa.repository;

import com.microservice.shoppingcart.infrastructure.jpa.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
