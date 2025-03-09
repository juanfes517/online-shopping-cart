package com.microservice.inventory.application.dto.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderRequestDTO {
    private List<SelectedProductRequestDTO> products;
}
