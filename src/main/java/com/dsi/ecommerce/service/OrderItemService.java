package com.dsi.ecommerce.service;
import com.dsi.ecommerce.exception.IdNotFoundException;
import com.dsi.ecommerce.model.order.OrderItem;

public interface OrderItemService {
    void saveNewOrderItem(OrderItem order);

    void deleteOrderItem(Long id);

    OrderItem getSingleOrderItem (Long id) throws IdNotFoundException;
}
