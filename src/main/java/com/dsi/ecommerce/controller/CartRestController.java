package com.dsi.ecommerce.controller;


import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartRestController {
        @Autowired
        private CartServiceImpl cartService;


        @PostMapping("/add-to-cart-product")
        public String addToCart (@RequestBody String id) {
                cartService.addCartItem(Long.parseLong(id));
                return "added item";
        }
}
