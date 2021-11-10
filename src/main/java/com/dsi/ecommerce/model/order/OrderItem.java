package com.dsi.ecommerce.model.order;

import com.dsi.ecommerce.model.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity(name = "order_items")
public class OrderItem {
    @Id
    @SequenceGenerator(name = "order_item_id_sequence", sequenceName = "order_item_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_item_id_sequence")
    private Long id;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Transient
    private Double subTotal;

    @OneToOne
    private Product product;

    @ManyToOne
    private Order order;
}
