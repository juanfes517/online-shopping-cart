package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.request.LoginRequestDTO;
import com.microservice.shoppingcart.application.dto.request.UserRequestDTO;
import com.microservice.shoppingcart.application.dto.response.AuthResponseDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.exception.UnmodifiableFieldException;
import com.microservice.shoppingcart.application.port.input.UserServicePort;
import com.microservice.shoppingcart.application.port.output.UserPersistencePort;
import com.microservice.shoppingcart.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort, UserDetailsService {

    private final UserPersistencePort userPersistencePort;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The username " + username + " was not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getRoleName()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                authorities
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO loginRequestDTO) {
        return null;
    }

    @Override
    public Authentication authenticate(String username, String password) {
        return null;
    }

    @Override
    public User CreateUser(UserRequestDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userPersistencePort.save(user);
    }

    @Override
    public User UpdateUser(UserRequestDTO userDTO) {
        if (userDTO.getUsername() == null) {
            throw new UnmodifiableFieldException("The username field cannot be modified");
        }
        if (userDTO.getEmail() == null) {
            throw new UnmodifiableFieldException("The email field cannot be modified");
        }

        User user = userPersistencePort.findByUsername(userDTO.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("The username was not found"));

        if (userDTO.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }
        if (userDTO.getFirstName() != null) {
            user.setFirstName(userDTO.getFirstName());
        }
        if (userDTO.getLastName() != null) {
            user.setLastName(userDTO.getLastName());
        }

        return userPersistencePort.save(user);
    }

    @Override
    public User DeleteUser(Long id) {
        return userPersistencePort.deleteById(id);
    }

    @Override
    public User GetUserById(Long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("The user was not found"));
    }
}
