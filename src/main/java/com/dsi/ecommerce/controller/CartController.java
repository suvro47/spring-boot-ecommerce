package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CartController {

        @Autowired
        private CartServiceImpl cartService;



        @GetMapping("/cart")
        public String home (Model model){

                List<CartItem> cartItemList = cartService.getAllCartItem();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("title", "Hello!!!!!!!!!!!!!!!");
                return "navbar";
        }
}
