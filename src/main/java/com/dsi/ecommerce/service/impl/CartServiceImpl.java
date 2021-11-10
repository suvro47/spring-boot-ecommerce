package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.CartService;

import java.util.List;

public class CartServiceImpl implements CartService {
        private CartDao cartDao;

        public List<CartItem> getAllCartItem(Cart cart){
                return cart.getCartItems();
        }
}
