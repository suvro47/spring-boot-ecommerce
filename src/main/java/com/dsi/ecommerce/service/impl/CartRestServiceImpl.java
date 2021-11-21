package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.CartService;
import com.dsi.ecommerce.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartRestServiceImpl {

        @Autowired
        private CartDao cartDao;

        @Autowired
        private CartItemDao cartItemDao;

        @Autowired
        private UserDao userDao;

        @Autowired
        private ProductDao productDao;

        public List<CartItem> getAllCartItem(MyUserDetail loggeduser) {
                User user = userDao.findById(loggeduser.getId()).orElse(new User());
                return user.getCart().getCartItems();
        }

        //        @Override
        public void addCartItem(Long id, MyUserDetail logoedUser) {
                User user = userDao.findById(logoedUser.getId()).orElse(new User());
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

        public void deleteCartItem(Long productId, MyUserDetail loggedUser) {
                User user = userDao.findById(loggedUser.getId()).orElse(new User());

                for (CartItem c : user.getCart().getCartItems()) {
                        if (c.getProduct().getId().equals(productId)) {
                                cartItemDao.delete(c);
                                break;
                        }
                }
        }

        public void clearCart(MyUserDetail loggedUser) {
                User user = userDao.findById(loggedUser.getId()).orElse(new User());
                cartItemDao.deleteAllInBatch(user.getCart().getCartItems());
        }

        public void increaseQuantity(Long productId, MyUserDetail principal) {
                User user = userDao.findById(principal.getId()).orElse(new User());
                for (CartItem c : user.getCart().getCartItems()) {
                        if (c.getProduct().getId().equals(productId)) {
                                CartItem cartItem = cartItemDao.findById(productId).orElse(new CartItem());
                                c.setQuantity(c.getQuantity() + 1);
                                cartItemDao.save(c);
                                break;
                        }
                }
        }


        public void decreaseQuantity(Long productId, MyUserDetail principal) {
                User user = userDao.findById(principal.getId()).orElse(new User());
                for (CartItem c : user.getCart().getCartItems()) {
                        if (c.getProduct().getId().equals(productId)) {
                                if (c.getQuantity() > 1){
                                        c.setQuantity(c.getQuantity() - 1);
                                        cartItemDao.save(c);
                                }
                                break;
                        }
                }
        }
}
