package com.microservice.shoppingcart.infrastructure.jpa.entity;

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

    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCartEntity shoppingCart;
}
