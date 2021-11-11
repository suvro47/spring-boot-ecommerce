package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

        @Autowired
        private CartDao cartDao;

        @Autowired
        private CartItemDao cartItemDao;


        public List<CartItem> getAllCartItem(Cart cart){
                return cart.getCartItems();
        }

        @Override
        public void addCartItem(CartItem cartItem) {
                cartItemDao.save(cartItem);
        }

        @Override
        public void deleteCartItem(CartItem cartItem) {
                cartItemDao.delete(cartItem);
        }

        @Override
        public void editCartItem(CartItem cartItem) {

                // Do editing stuff here
                cartItemDao.save(cartItem);
        }
}
