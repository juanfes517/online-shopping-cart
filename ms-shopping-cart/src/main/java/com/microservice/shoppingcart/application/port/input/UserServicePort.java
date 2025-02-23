package com.microservice.shoppingcart.application.port.input;

import com.microservice.shoppingcart.application.dto.UserDTO;
import com.microservice.shoppingcart.application.dto.request.LoginRequestDTO;
import com.microservice.shoppingcart.application.dto.response.AuthResponseDTO;
import org.springframework.security.core.Authentication;

public interface UserServicePort {
    AuthResponseDTO login(LoginRequestDTO loginRequestDTO);
    Authentication authenticate(String username, String password);
    UserDTO CreateUser(UserDTO userDTO);
    UserDTO UpdateUser(UserDTO userDTO);
    UserDTO DeleteUser(Long id);
    UserDTO GetUserById(Long id);
}
