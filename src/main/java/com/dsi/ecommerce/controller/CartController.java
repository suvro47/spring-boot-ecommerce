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
        public String getAllCartItems (@AuthenticationPrincipal MyUserDetail loggeduser, Model model){
                List<CartItem> cartItemList = cartService.getAllCartItem(loggeduser);
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", cartService.getTotalCost());
                return "navbar";
        }

        @RequestMapping("/add-to-cart-product/{id}")
        public String addToCart (@PathVariable(value = "id") Long id) {
                cartService.addCartItem(id);
                return "redirect:/cart";
        }

        @RequestMapping("/delete-cart-item/{id}")
        public String deleteFromCart (@PathVariable(value = "id") Long id){
                cartService.deleteCartItem(id);
                return "redirect:/cart";
        }

        @RequestMapping("/clear-cart")
        public String clearCart (){
                cartService.clearCart();
                return "redirect:/cart";
        }

        @RequestMapping("/increase-cart-item-quantity/{id}")
        public String increaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.increaseQuanity(id);
                return "redirect:/cart";
        }

        @RequestMapping("/decrease-cart-item-quantity/{id}")
        public String decreaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.decreaseQuanity(id);
                return "redirect:/cart";
        }
}
