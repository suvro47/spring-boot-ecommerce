package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String showHomePage(Model model) {
        return "index";
    }

}
