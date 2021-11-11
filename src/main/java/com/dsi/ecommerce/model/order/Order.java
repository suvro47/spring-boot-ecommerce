package com.dsi.ecommerce.model.order;

import com.dsi.ecommerce.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Setter
@Getter
@ToString
@Entity(name = "orders")
public class Order {
    @Id
    @SequenceGenerator(name = "order_id_sequence", sequenceName = "order_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "order_date_time_millis", nullable = false)
    private Long orderDateTimeMillis;

    @Column(name = "total_price", nullable = false)
    private Integer totalPrice;

    @Column(name = "delivery_address", nullable = false, columnDefinition = "TEXT")
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_status")
    private OrderStatus orderStatus;

    @ManyToOne
    private User user;

    @OneToOne
    private Payment payment;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItem;
}
