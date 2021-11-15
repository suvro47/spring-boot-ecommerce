package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.model.cart.CartItem;

public interface CartService {

        public void addCartItem(CartItem cartItem);
        public void deleteCartItem(CartItem cartItem);
        public void editCartItem(CartItem cartItem);
}
