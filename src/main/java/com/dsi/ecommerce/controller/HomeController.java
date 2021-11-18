package com.dsi.ecommerce.controller;


import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    private CartServiceImpl cartService;

    @GetMapping("/")
    public String homePage(@AuthenticationPrincipal MyUserDetail principal, Model model ) {
        List<CartItem> cartItemList = principal == null ? new ArrayList<CartItem>() : cartService.getAllCartItem(principal);

        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost", principal == null ? 0.0 : cartService.getTotalCost());
        return "index";
    }
}
