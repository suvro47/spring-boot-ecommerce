package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.OrderItemDao;
import com.dsi.ecommerce.exception.IdNotFoundException;
import com.dsi.ecommerce.model.order.OrderItem;
import com.dsi.ecommerce.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderItemServiceImpl(OrderItemDao orderItemDao){
        this.orderItemDao = orderItemDao;
    }

    @Override
    public void saveNewOrderItem(OrderItem orderItem) {
        orderItemDao.save(orderItem);
    }

    @Override
    public void deleteOrderItem(Long id) {
        orderItemDao.deleteById(id);
    }

    @Override
    public OrderItem getSingleOrderItem(Long id) throws IdNotFoundException {
        return orderItemDao.findById(id).orElseThrow(() -> new IdNotFoundException("OrderItem not found in the request."));
    }
}
