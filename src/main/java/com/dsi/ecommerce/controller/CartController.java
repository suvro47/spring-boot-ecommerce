package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CartController {

        @Autowired
        private CartServiceImpl cartService;
        @Autowired
        private ProductDao productDao;

        @GetMapping("/cart")
        public String home (Model model){

                List<CartItem> cartItemList = cartService.getAllCartItem();
                model.addAttribute("cartItems", cartItemList);
                return "navbar";
        }

        @RequestMapping("/add-to-cart")
        public String addToCart (Model model){

//                making fake cartitem
                Product product = new Product();
                product.setCategory("Vegitables");
                product.setDescription("description");
                product.setPrice(567.5);
                product.setName("Tomato");
                product.setSoldItems(3);
                product.setAvailableQuantity(70);
                product = productDao.save(product);

                CartItem cartItem = new CartItem();
                cartItem.setProduct(product);
                cartItem.setQuantity(10);

                cartService.addCartItem(cartItem);
                return home(model);
        }
}
