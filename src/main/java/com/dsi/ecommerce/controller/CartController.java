package com.dsi.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
        @GetMapping("/cart/user/{id}")
        public String home (){

                return "cart/index";
        }
}
