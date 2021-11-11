package com.dsi.ecommerce.service;

import com.dsi.ecommerce.exception.IdNotFoundException;
import com.dsi.ecommerce.model.order.Order;

public interface OrderService {
    void saveNewOrder(Order order);

    void orderDelete(Long id);

    Order getSingleOrder(Long id) throws IdNotFoundException;
}
