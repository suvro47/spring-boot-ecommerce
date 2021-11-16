package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.User;
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

        @Autowired
        private UserDao userDao;

        public List<CartItem> getAllCartItem(){
                User user = userDao.findById(1L).orElse(new User());
                return user.getCart().getCartItems();
        }

        @Override
        public void addCartItem(CartItem cartItem) {

                User user = userDao.findById(1L).orElse(new User());

                cartItem.setCart(user.getCart());
                cartItem = cartItemDao.save(cartItem);
                user.getCart().getCartItems().add(cartItem);

                cartDao.save(user.getCart());
                userDao.save(user);
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

        @Override
        public void clearCart() {
                User currentUser = userDao.findById(2L).orElse(new User());
                cartItemDao.deleteAllInBatch(currentUser.getCart().getCartItems());
        }
}
