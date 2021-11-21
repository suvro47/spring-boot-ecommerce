package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.impl.CartRestServiceImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CartRestController {

        @Autowired
        private CartRestServiceImpl cartService;

        @PostMapping("/add-to-cart")
        public void addToCart (@RequestBody String jsonStr, @AuthenticationPrincipal MyUserDetail principal ) throws JSONException, ResourceNotFoundException {
                JSONObject obj = new JSONObject(jsonStr);
                String productId = obj.getString("productId");
                String quantity = obj.getString("quantity");
                //System.out.println( "product Id : " + productId + " Quantity : " + quantity );
                cartService.addCartItem(Long.parseLong(productId), Integer.parseInt(quantity), principal);
                System.out.println(" Add to cart service called .............");
        }

        @PostMapping("/delete-cart-item")
        public void deleteCartItem( @AuthenticationPrincipal MyUserDetail principal, @RequestBody String id ){
                cartService.deleteCartItem(Long.parseLong(id), principal);
        }

        @PostMapping("/clear-cart")
        public void clearCart(@AuthenticationPrincipal MyUserDetail principal){
                cartService.clearCart(principal);
        }

        @PostMapping("/increase-quantity")
        public void increaseQuantity(@AuthenticationPrincipal MyUserDetail principal, @RequestBody String productid){
                cartService.increaseQuantity(Long.parseLong(productid), principal);
        }

        @PostMapping("/decrease-quantity")
        public void decreaseQuantity(@AuthenticationPrincipal MyUserDetail principal, @RequestBody String productid){
                cartService.decreaseQuantity(Long.parseLong(productid), principal);
        }
}
