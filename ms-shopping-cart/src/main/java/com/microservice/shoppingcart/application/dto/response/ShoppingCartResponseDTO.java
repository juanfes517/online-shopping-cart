package com.microservice.shoppingcart.application.dto.response;

import com.microservice.shoppingcart.domain.model.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShoppingCartResponseDTO {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createdAt;
    private double totalPrice;
    private User user;
    private Set<SelectedProductResponseDTO> selectedProducts;
}
