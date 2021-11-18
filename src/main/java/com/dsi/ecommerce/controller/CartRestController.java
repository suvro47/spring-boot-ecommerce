package com.dsi.ecommerce.controller;


import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.impl.CartRestServiceImpl;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartRestController {
        @Autowired
        private CartRestServiceImpl cartService;


        @PostMapping("/add-to-cart")
        public String addToCart (@RequestBody String id, @AuthenticationPrincipal MyUserDetail principal ) {
                cartService.addCartItem(Long.parseLong(id), principal);
                return "added item";
        }

        @PostMapping("/delete-cart-item")
        public void deleteCartItem( @AuthenticationPrincipal MyUserDetail principal, @RequestBody String id ){
                cartService.deleteCartItem(Long.parseLong(id), principal);
        }

        @PostMapping("/clear-cart")
        public void clearCart(@AuthenticationPrincipal MyUserDetail principal){
                cartService.clearCart(principal);
        }

        @PostMapping("/increase-quantity")
        public void increaseQuantity(@AuthenticationPrincipal MyUserDetail principal, @RequestBody String productid){
                cartService.increaseQuantity(Long.parseLong(productid), principal);
        }

        @PostMapping("/decrease-quantity")
        public void decreaseQuantity(@AuthenticationPrincipal MyUserDetail principal, @RequestBody String productid){
                cartService.decreaseQuantity(Long.parseLong(productid), principal);
        }
}
