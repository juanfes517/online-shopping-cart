package com.microservice.shoppingcart.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Rol {
    private Long id;
    private String rolName;
}
