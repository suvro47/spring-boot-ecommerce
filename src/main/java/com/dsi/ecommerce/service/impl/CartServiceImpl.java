package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.MyUserDetail;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.CartService;
import com.dsi.ecommerce.model.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

        @Autowired
        private CartDao cartDao;

        @Autowired
        private CartItemDao cartItemDao;

        @Autowired
        private UserDao userDao;

        @Autowired
        private ProductDao productDao;

        public List<CartItem> getAllCartItem(MyUserDetail loggeduser){
                User user = userDao.findById(loggeduser.getId()).orElse(new User());
                return user.getCart().getCartItems();
        }


        @Override
        public void addCartItem(Long id) {
                User user = userDao.findById(3L).orElse(new User());
                Product product = productDao.findById(id).orElse(new Product());

                CartItem cartItem = new CartItem();

                cartItem.setCart(user.getCart());
                cartItem.setProduct(product);
                cartItem.setQuantity(1);

                cartItem = cartItemDao.save(cartItem);
                user.getCart().getCartItems().add(cartItem);

                cartDao.save(user.getCart());
                userDao.save(user);
        }

        @Override
        public void deleteCartItem(Long id) {
                CartItem cartItem = cartItemDao.findById(id).orElse(new CartItem());
                cartItemDao.delete(cartItem);
        }

        @Override
        public void increaseQuanity(Long id) {
                CartItem cartItem = cartItemDao.findById(id).orElse(new CartItem());
                cartItem.setQuantity(cartItem.getQuantity() + 1);
                cartItemDao.save(cartItem);
        }

        @Override
        public void decreaseQuanity(Long id) {
                CartItem cartItem = cartItemDao.findById(id).orElse(new CartItem());
                cartItem.setQuantity(cartItem.getQuantity() - 1);
                cartItemDao.save(cartItem);
        }

        @Override
        public void clearCart() {
                User user = userDao.findById(1L).orElse(new User());
                cartItemDao.deleteAllInBatch(user.getCart().getCartItems());
        }

        @Override
        public Double getTotalCost() {
                User user = userDao.findById(1L).orElse(new User());
                return user.getCart().getTotalCost();
        }

}
