package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.order.Order;
import com.dsi.ecommerce.model.order.OrderStatus;
import com.dsi.ecommerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public String createOrderForm(Model model){
        Order order = new Order();
        model.addAttribute("order", new Order());
        return "create_order";
    }

    @PostMapping("/create")
    public void createOrder(Order order){
        order.setOrderDateTimeMillis(System.currentTimeMillis());
        order.setOrderStatus(OrderStatus.PLACED);
        order.setUser(new User());
        orderService.saveNewOrder(order);
    }
}
