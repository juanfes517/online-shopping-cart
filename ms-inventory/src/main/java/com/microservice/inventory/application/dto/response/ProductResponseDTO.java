package com.microservice.inventory.application.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {
    private String productCode;
    private String name;
    private String description;
    private double rating;
    private double price;
    private int availableQuantity;
}
