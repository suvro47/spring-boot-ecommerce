package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.OrderDao;
import com.dsi.ecommerce.exception.IdNotFoundException;
import com.dsi.ecommerce.model.order.Order;
import com.dsi.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Override
    public void saveNewOrder(Order order) {
        orderDao.save(order);
    }

    @Override
    public void orderDelete(Long id) {
        orderDao.deleteById(id);
    }

    @Override
    public Order getSingleOrder(Long id) throws IdNotFoundException {
        return orderDao.findById(id).orElseThrow(() -> new IdNotFoundException("Order not found in the request."));
    }
}
