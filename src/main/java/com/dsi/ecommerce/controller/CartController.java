package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class CartController {

        @Autowired
        private CartServiceImpl cartService;

//        @Autowired
//        private ProductDao productDao;

        @GetMapping("/cart")
        public String getAllCartItems (Model model){
                List<CartItem> cartItemList = cartService.getAllCartItem();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", cartService.getTotalCost());
                return "navbar";
        }

        @RequestMapping("/add-to-cart-product/{id}")
        public String addToCart (@PathVariable(value = "id") Long id) {
                cartService.addCartItem(id);
                return "navbar";
        }
//                Product product = new Product();
//                product.setCategory("Spice");
//                product.setDescription("Spicy");
//                product.setPrice(567.5);
//                product.setName("Ginger");
//                product.setSoldItems(232);
//                product.setAvailableQuantity(388);
//                product = productDao.save(product);
//
//                CartItem cartItem = new CartItem();
//                cartItem.setProduct(product);
//                cartItem.setQuantity(2);
//
//                cartService.addCartItem(cartItem);
//                return home(model);
//        }

        @RequestMapping("/delete-cart-item/{id}")
        public String deleteFromCart (@PathVariable(value = "id") Long id){
                cartService.deleteCartItem(id);
                return "navbar";
        }

        @RequestMapping("/clear-cart")
        public String clearCart (){
                cartService.clearCart();
                return "navbar";
        }

        @RequestMapping("/increase-cart-item-quantity/{id}")
        public String increaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.increaseQuanity(id);
                return "navbar";
        }

        @RequestMapping("/decrease-cart-item-quantity/{id}")
        public String decreaseQuantity (@PathVariable(value = "id") Long id) {
                cartService.decreaseQuanity(id);
                return "navbar";
        }
}
