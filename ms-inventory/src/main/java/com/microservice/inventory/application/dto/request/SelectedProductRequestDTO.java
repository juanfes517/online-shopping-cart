package com.microservice.inventory.application.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedProductRequestDTO {
    private String productCode;
    private String name;
    private double price;
    private int amount;
}
