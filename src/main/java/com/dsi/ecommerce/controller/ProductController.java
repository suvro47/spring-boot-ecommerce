package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    @RequestMapping(value = "/add-product", method = RequestMethod.GET)
    public String getProductAddForm(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("product", productDTO);
        return "product/products_add_form";
    }

}
