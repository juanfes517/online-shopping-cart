package com.microservice.shoppingcart.infrastructure.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "selected_products")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SelectedProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "product-code", unique = true)
    private String productCode;

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "shopping_cart_id")
    @JsonIgnore
    private ShoppingCartEntity shoppingCart;
}
