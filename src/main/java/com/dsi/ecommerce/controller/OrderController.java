package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.order.Order;
import com.dsi.ecommerce.model.order.OrderStatus;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.OrderService;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createOrderForm(Model model){
        Order order = new Order();
        //order.setUser(userService.getUserFromMyUserDetail(principal));
        model.addAttribute("order", order);
        return "create_order";
    }

    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal MyUserDetail principal, Order order){
        order.setUser(userService.getUserFromMyUserDetail(principal));
        order.setOrderDateTimeMillis(System.currentTimeMillis());
        order.setOrderStatus(OrderStatus.PLACED);
        orderService.saveNewOrder(order);
        return "redirect:/";
    }
}
