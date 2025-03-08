package com.microservice.shoppingcart.application.dto.response;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedProductResponseDTO {
    private String productCode;
    private String name;
    private double price;
    private int amount;
}
