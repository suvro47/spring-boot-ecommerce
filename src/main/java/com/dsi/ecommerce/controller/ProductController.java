package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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

    @RequestMapping("/users/shop/{id}/product/{id2}")
    public String getProduct(Model model, @PathVariable(value="id") Long shopId , @PathVariable(value="id2") Long productId ) {

        try {
            Product product = productService.getProduct(shopId, 3l);
            model.addAttribute("product", product);
            System.out.println(product.getName());
        } catch (Exception e) {
            System.out.println("Exception Occur : " + e );
        }
        return "product/product";
    }






}
