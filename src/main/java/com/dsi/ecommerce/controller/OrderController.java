package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.model.order.Order;
import com.dsi.ecommerce.model.order.OrderItem;
import com.dsi.ecommerce.model.order.OrderStatus;
import com.dsi.ecommerce.model.order.PaymentStatus;
import com.dsi.ecommerce.service.CartService;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.OrderService;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;

    @GetMapping("/create")
    public String createOrderForm(Model model){
        Order order = new Order();
        model.addAttribute("order", order);
        return "create_order";
    }

    @Transactional
    @PostMapping("/create")
    public String createOrder(@AuthenticationPrincipal MyUserDetail principal, Order order){
        User currentUser = userService.getUserFromMyUserDetail(principal);
        Cart currentCart = currentUser.getCart();
        order.setUser(currentUser);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setOrderDateTimeMillis(System.currentTimeMillis());
        order.setOrderItems(OrderItem.getOrderItemsFromCartItems(currentCart.getCartItems()));

        orderService.saveNewOrder(order);

        cartService.clearCart();
        return "redirect:/";
    }

    @GetMapping("/getAllOrders")
    public String getAllOrders(@AuthenticationPrincipal MyUserDetail principal){
        User currentUser = userService.getUserFromMyUserDetail(principal);

        for (Order order : currentUser.getOrderList()){
            System.out.println(order.getTotalPrice());
            for(OrderItem orderItem: order.getOrderItems()){
                System.out.println("Name: "+orderItem.getProduct().getName());
            }
        }
        //currentUser.getOrderList().stream().forEach(order -> System.out.println(order.getDeliveryAddress()));
        return "redirect:/order/create";
    }
}
