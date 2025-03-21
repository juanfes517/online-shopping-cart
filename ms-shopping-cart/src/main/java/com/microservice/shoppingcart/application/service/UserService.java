package com.microservice.shoppingcart.application.service;

import com.microservice.shoppingcart.application.dto.request.LoginRequestDTO;
import com.microservice.shoppingcart.application.dto.request.UserRequestDTO;
import com.microservice.shoppingcart.application.dto.response.AuthResponseDTO;
import com.microservice.shoppingcart.application.dto.response.RoleResponseDTO;
import com.microservice.shoppingcart.application.exception.NotFoundException;
import com.microservice.shoppingcart.application.exception.UnmodifiableFieldException;
import com.microservice.shoppingcart.application.port.input.RoleServicePort;
import com.microservice.shoppingcart.application.port.input.UserServicePort;
import com.microservice.shoppingcart.application.port.output.UserPersistencePort;
import com.microservice.shoppingcart.domain.model.Role;
import com.microservice.shoppingcart.domain.model.User;
import com.microservice.shoppingcart.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final PasswordEncoder passwordEncoder;
    private final RoleServicePort roleServicePort;
    private final ModelMapper modelMapper;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPersistencePort.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("The username " + username + " was not found"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("ROLE_".concat(user.getRole().getRoleName())));

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
        String username = loginRequestDTO.getUsername();
        String password = loginRequestDTO.getPassword();

        Authentication authentication = this.authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtUtils.createToken(authentication);

        return AuthResponseDTO.builder()
                .username(username)
                .message("User loged succesfully")
                .token(accessToken)
                .role(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString())
                .build();
    }

    @Override
    public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (userDetails == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public User createUser(UserRequestDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        RoleResponseDTO defaultRoleDTO = modelMapper.map(roleServicePort.findRol(2L), RoleResponseDTO.class);

        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setRole(modelMapper.map(defaultRoleDTO, Role.class));

        return userPersistencePort.save(user);
    }

    @Override
    public User updateUser(UserRequestDTO userDTO) {
        if (userDTO.getEmail() != null) {
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
    public User deleteUser(Long id) {
        User deletedUser = this.getUserById(id);
        userPersistencePort.delete(deletedUser);
        return deletedUser;
    }

    @Override
    public User getUserById(Long id) {
        return userPersistencePort.findById(id)
                .orElseThrow(() -> new NotFoundException("The user was not found"));
    }
}
