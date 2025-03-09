package com.microservice.inventory.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String name;
    private String description;
    private double rating;
    private double price;
    private int availableQuantity;

    public boolean hasSufficientStock(int quantity) {
        return this.availableQuantity >= quantity;
    }

    public int substractStock(int quantity) {
        if (!hasSufficientStock(quantity)) {
            throw new IndexOutOfBoundsException("Insufficient stock for " + this.name);
        }
        this.availableQuantity -= quantity;
        return this.availableQuantity;
    }
}
