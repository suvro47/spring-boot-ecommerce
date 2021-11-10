package com.dsi.ecommerce.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cart_items")
public class CartItem {

        @Id
        @SequenceGenerator(name = "cart_item_id_sequence", sequenceName = "cart_item_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_sequence")
        private Long id;

//        @OneToOne
//        private Product product;

        @Column(nullable = false)
        private Integer quantity;

        @Transient
        private Double subTotal;

        public Double getSubTotal() {
                return this.quantity * this.product.getPrice();
        }
}
