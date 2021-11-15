package com.dsi.ecommerce.model.cart;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity(name = "carts")
public class Cart {

        @Id
        @SequenceGenerator(name = "cart_id_sequence", sequenceName = "cart_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_id_sequence")
        private Long id;

        @Transient
        private Double totalCost;

        @OneToMany(mappedBy = "cart")
        private List<CartItem> cartItems = new ArrayList<>();

        public Double getTotalCost() {
                Double total = 0.0;
                for(CartItem c : cartItems){
                        total += c.getSubTotal();
                }
                return total;
        }
}
