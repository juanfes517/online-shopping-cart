package com.microservice.shoppingcart.application.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
