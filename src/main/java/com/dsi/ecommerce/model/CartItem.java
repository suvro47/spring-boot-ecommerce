package com.dsi.ecommerce.model;

import javax.persistence.*;

@Entity(name = "cart_item")
public class CartItem {

        @Id
        @SequenceGenerator(name = "cart_item_id_sequence", sequenceName = "cart_item_id_sequence", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_item_id_sequence")
        private Long id;

//        @OneToOne
//        private Product product;

        @Column(nullable = false)
        private Integer quantity;

//        @ManyToOne
//        private User user;

        //Setters and Getters
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Integer getQuantity() {
                return quantity;
        }

        public void setQuantity(Integer quantity) {
                this.quantity = quantity;
        }

//        public Product getProduct() {
//                return product;
//        }
//
//        public void setProduct(Product product) {
//                this.product = product;
//        }
//
//        public User getUser() {
//                return user;
//        }
//
//        public void setUser(User user) {
//                this.user = user;
//        }
}
