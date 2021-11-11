package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {

        @Autowired
        private CartServiceImpl cartService;

        @GetMapping("/cart/user/{id}")
        public String home (){

//                List<CartItem> cartItemList = cartService.getAllCartItem()

                return "cart/index";
        }
}
