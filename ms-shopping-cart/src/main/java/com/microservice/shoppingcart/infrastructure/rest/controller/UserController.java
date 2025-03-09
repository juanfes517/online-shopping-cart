package com.microservice.shoppingcart.infrastructure.rest.controller;

import com.microservice.shoppingcart.application.dto.request.UserRequestDTO;
import com.microservice.shoppingcart.application.dto.response.UserResponseDTO;
import com.microservice.shoppingcart.application.service.UserService;
import com.microservice.shoppingcart.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseDTO.class));
    }

    @PatchMapping
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserRequestDTO userRequestDTO) {
        User user = userService.updateUser(userRequestDTO);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseDTO.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDTO> deleteUser(@PathVariable Long id) {
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(modelMapper.map(user, UserResponseDTO.class));
    }
}
