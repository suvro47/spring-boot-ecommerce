package com.dsi.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @GetMapping("/signup")
    public String signupForm(){
        return "auth/signup";
    }


    @PostMapping("/signup")
    public String signup(){
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(){
        return  "auth/login";
    }

    @PostMapping("/login")
    public String login(){
        return "redirect:/";
    }


}
