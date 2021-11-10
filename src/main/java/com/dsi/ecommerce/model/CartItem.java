package com.dsi.ecommerce.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "cart_items")
public class CartItem {

        @Id
        @SequenceGenerator(name = "cart_item_id_sequence", sequenceName = "cart_item_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_sequence")
        private Long id;

        @Column(nullable = false)
        private Integer quantity;

        @Transient
        private Double subTotal;

        @ManyToOne
        private Product product;

        @ManyToOne
        private Cart cart;

        public Double getSubTotal() {
                return this.quantity * this.product.getPrice();
        }
}
