package com.microservice.shoppingcart.domain.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private double totalPrice;
    private User user;
    private Set<SelectedProduct> selectedProducts;


    public void calculateTotalPrice() {
        double total = 0.0;
        for (SelectedProduct selectedProduct : selectedProducts) {
            total += selectedProduct.getPrice() * selectedProduct.getAmount();
        }
        this.totalPrice = total;
    }
}
