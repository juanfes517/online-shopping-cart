package com.microservice.inventory.domain.model;

import com.microservice.inventory.domain.exception.InsufficientStockException;
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
            throw new InsufficientStockException("Insufficient stock for " + this.name);
        }
        return true;
    }

    public int substractStock(int quantity) {
        this.availableQuantity -= quantity;
        return this.availableQuantity;
    }
}
