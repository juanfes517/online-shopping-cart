package com.microservice.shoppingcart.application.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private String message;
    private String username;
    private String role;
}
