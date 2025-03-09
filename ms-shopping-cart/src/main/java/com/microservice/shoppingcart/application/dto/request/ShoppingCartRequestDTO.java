package com.microservice.shoppingcart.application.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartRequestDTO {
    private Long userId;
    private String name;
    private String description;
}
