package com.dsi.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "carts")
public class Cart {
        @Id
        @SequenceGenerator(name = "cart_id_sequence", sequenceName = "cart_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_sequence")
        private Long id;

        @OneToMany
        @JoinTable(name = "list_cart_items")
        private List<CartItem> cartItems;
}
