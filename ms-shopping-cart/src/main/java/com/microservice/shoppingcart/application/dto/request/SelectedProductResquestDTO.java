package com.microservice.shoppingcart.application.dto.request;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SelectedProductResquestDTO {
    private String productCode;
    private String name;
    private double price;
    private int amount;
}
