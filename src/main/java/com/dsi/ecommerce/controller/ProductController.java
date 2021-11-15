package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.ProductService;
import com.dsi.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    private ShopService shopService;

    @RequestMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);
        return "product/products";
    }

    @RequestMapping(value = "/shop/{shop_id}/add-product", method = RequestMethod.GET)
    public String getProductAddForm(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="shop_id") Long shopID, Model model) {
        try {
            Shop shop = shopService.getShop(principal);
            if (shop.getId() == shopID){
                ProductDTO productDTO = new ProductDTO();
                model.addAttribute("product", productDTO);
                model.addAttribute("shop_id", shopID);
                return "product/products_add_form";
            }
            else {
                System.out.println("-----------------Permission Denied!----------------");
                return "index";
            }
        } catch (Exception e) {
            System.out.println("An exception occured ");
            return "index";
        }
    }

    @RequestMapping(value = "/shop/{shop_id}/add-product", method = RequestMethod.POST)
    public String addProduct(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="shop_id") Long shopID, Model model) {
        System.out.println("Hello");
        return "index";
//        try {
//            Shop shop = shopService.getShop(principal);
//            if (shop.getId() == shopID){
//                ProductDTO productDTO = new ProductDTO();
//                model.addAttribute("product", productDTO);
//                return "product/products_add_form";
//            }
//            else {
//                System.out.println("-----------------Permission Denied!----------------");
//                return "index";
//            }
//        } catch (Exception e) {
//            System.out.println("An exception occured ");
//            return "index";
//        }
    }

}
