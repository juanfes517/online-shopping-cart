package com.microservice.shoppingcart.infrastructure.jpa.adapter;

import com.microservice.shoppingcart.application.port.output.UserPersistencePort;
import com.microservice.shoppingcart.domain.model.User;
import com.microservice.shoppingcart.infrastructure.jpa.entity.UserEntity;
import com.microservice.shoppingcart.infrastructure.jpa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserJpaAdapter implements UserPersistencePort {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id)
                .map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userEntity -> modelMapper.map(userEntity, User.class));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, User.class))
                .toList();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userRepository.save(
                modelMapper.map(user, UserEntity.class));

        return modelMapper.map(userEntity, User.class);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(
                modelMapper.map(user, UserEntity.class));
    }
}
