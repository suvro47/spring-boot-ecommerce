package com.dsi.ecommerce.service;

import com.dsi.ecommerce.model.cart.CartItem;
import java.util.List;

public interface CartService {
        public List<CartItem> getAllCartItem();
        public void addCartItem(Long id);
        public void deleteCartItem(Long id);
        public void increaseQuanity(Long id);
        public void decreaseQuanity(Long id);
        public void clearCart();
        public Double getTotalCost();
}
