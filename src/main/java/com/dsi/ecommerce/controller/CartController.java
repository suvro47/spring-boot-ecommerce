package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CartController {

        @Autowired
        private CartServiceImpl cartService;

        @GetMapping("/cart")
        public String getAllCartItems (@AuthenticationPrincipal MyUserDetail principal, Model model){
                List<CartItem> cartItemList = cartService.getAllCartItem(principal);
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", cartService.getTotalCost());
                return "navbar";
        }

        @PostMapping("/add-to-cart-product/{id}")
        public String addToCart (@PathVariable(value = "id") Long id) {
                cartService.addCartItem(id);
                return "redirect:/cart";
        }

        @DeleteMapping("/delete-cart-item/{id}")
        public String deleteFromCart (@PathVariable(value = "id") Long id){
                cartService.deleteCartItem(id);
                return "redirect:/cart";
        }

        @DeleteMapping("/clear-cart")
        public String clearCart (){
                cartService.clearCart();
                return "redirect:/cart";
        }

        @PostMapping("/increase-cart-item-quantity/{id}")
        public String increaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.increaseQuanity(id);
                return "redirect:/cart";
        }

        @PostMapping("/decrease-cart-item-quantity/{id}")
        public String decreaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.decreaseQuanity(id);
                return "redirect:/cart";
        }
}
