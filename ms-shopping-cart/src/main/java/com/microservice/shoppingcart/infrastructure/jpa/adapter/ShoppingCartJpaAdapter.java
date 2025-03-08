package com.microservice.shoppingcart.infrastructure.jpa.adapter;

import com.microservice.shoppingcart.application.port.output.ShoppingCartPersistencePort;
import com.microservice.shoppingcart.domain.model.ShoppingCart;
import com.microservice.shoppingcart.domain.model.User;
import com.microservice.shoppingcart.infrastructure.jpa.entity.ShoppingCartEntity;
import com.microservice.shoppingcart.infrastructure.jpa.entity.UserEntity;
import com.microservice.shoppingcart.infrastructure.jpa.repository.ShoppingCartRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ShoppingCartJpaAdapter implements ShoppingCartPersistencePort {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ShoppingCart> findAllByUser(User user) {
        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        return shoppingCartRepository.findAllByUser(userEntity).stream()
                .map(shoppingCartEntity -> modelMapper.map(shoppingCartEntity, ShoppingCart.class))
                .toList();
    }

    @Override
    public Optional<ShoppingCart> findById(Long id) {
        return shoppingCartRepository.findById(id)
                .map(shoppingCartEntity -> modelMapper.map(shoppingCartEntity, ShoppingCart.class));
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        ShoppingCartEntity shoppingCartEntity = shoppingCartRepository.save(
                modelMapper.map(shoppingCart, ShoppingCartEntity.class));

        return modelMapper.map(shoppingCartEntity, ShoppingCart.class);
    }
}
