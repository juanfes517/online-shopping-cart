package com.microservice.shoppingcart.application.dto.response;

import com.microservice.shoppingcart.domain.model.ShoppingCart;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private Long id;
    private String name;
    private String description;
    private double rating;
    private double price;
    private int availableQuantity;
}
