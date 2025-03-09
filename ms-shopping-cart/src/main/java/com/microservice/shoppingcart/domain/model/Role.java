package com.microservice.shoppingcart.domain.model;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String roleName;
}
