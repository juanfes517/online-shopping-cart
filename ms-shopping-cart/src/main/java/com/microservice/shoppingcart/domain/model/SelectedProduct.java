package com.microservice.shoppingcart.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SelectedProduct {
    private Long id;
    private String name;
    private double price;
    private int amount;
    private ShoppingCart shoppingCart;
}
