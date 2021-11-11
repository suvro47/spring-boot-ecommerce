package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String getHomeMovies(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

}
