package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.request.LoginRequestDTO;
import com.microservice.shoppingcart.application.dto.request.UserRequestDTO;
import com.microservice.shoppingcart.application.dto.response.AuthResponseDTO;
import com.microservice.shoppingcart.application.dto.response.UserResponseDTO;
import org.springframework.security.core.Authentication;

public interface UserServicePort {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
    Authentication authenticate(String username, String password);
    UserResponseDTO CreateUser(UserRequestDTO userDTO);
    UserResponseDTO UpdateUser(UserRequestDTO userDTO);
    UserResponseDTO DeleteUser(Long id);
    UserResponseDTO GetUserById(Long id);
}
