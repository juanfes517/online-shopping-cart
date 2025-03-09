package com.microservice.inventory.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long id;
    private String productCode;
    private String name;
    private String description;
    private double rating;
    private double price;
    private int availableQuantity;

    public boolean canPurchase(int quantity) {
        if (availableQuantity < quantity) {
            throw new IndexOutOfBoundsException("Insufficient stock for " + this.name);
        }
        return true;
    }

    public int substractStock(int quantity) {
        this.availableQuantity -= quantity;
        return this.availableQuantity;
    }
}
