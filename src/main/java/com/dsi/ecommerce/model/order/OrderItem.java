package com.dsi.ecommerce.model.order;

import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.cart.CartItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(name = "order_id")
    private Long orderId;

    public static OrderItem getSingleOrderItemFromCartItem(CartItem cartItem){
        OrderItem orderItem = new OrderItem();
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setSubTotal(cartItem.getSubTotal());

        return orderItem;
    }

    public static List<OrderItem> getOrderItemsFromCartItems(List<CartItem> cartItems){
        return cartItems.stream().map(OrderItem::getSingleOrderItemFromCartItem).collect(Collectors.toList());
    }

    public Double getSubTotal() {
        return this.quantity * this.product.getPrice();
    }
}
