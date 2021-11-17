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
    public String homePage( Model model ) {
        List<CartItem> cartItemList = new ArrayList<>();
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost", 0.0);
        return "index";
    }
}
