package com.dsi.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.plaf.synth.SynthDesktopIconUI;

@Controller
public class CartController {
        @GetMapping("/cart/user/{id}")
        public String home (){

                System.out.println("adlsfkjalsfjlasjdflajsflj");

                return "cart/index";
        }

}
