package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.request.LoginRequestDTO;
import com.microservice.shoppingcart.application.dto.request.UserRequestDTO;
import com.microservice.shoppingcart.application.dto.response.AuthResponseDTO;
import com.microservice.shoppingcart.application.dto.response.UserResponseDTO;
import com.microservice.shoppingcart.domain.model.User;
import org.springframework.security.core.Authentication;

public interface UserServicePort {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
    Authentication authenticate(String username, String password);
    User CreateUser(UserRequestDTO userDTO);
    User UpdateUser(UserRequestDTO userDTO);
    User DeleteUser(Long id);
    User GetUserById(Long id);
}
