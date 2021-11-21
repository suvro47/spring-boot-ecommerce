package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.CartRestService;
import com.dsi.ecommerce.service.MyUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartRestServiceImpl implements CartRestService {

        @Autowired
        private CartDao cartDao;

        @Autowired
        private CartItemDao cartItemDao;

        @Autowired
        private UserDao userDao;

        @Autowired
        private ProductDao productDao;

        @Override
        public List<CartItem> getAllCartItem(MyUserDetail loggeduser) {
                User user = userDao.findById(loggeduser.getId()).orElse(new User());
                return user.getCart().getCartItems();
        }

        @Override
        public void addCartItem(long productId,Integer quantity, MyUserDetail logoedUser) throws ResourceNotFoundException {

                User user = userDao.findById(logoedUser.getId())
                        .orElseThrow( () -> new ResourceNotFoundException("User not found"));
                Product product = productDao.findById(productId)
                        .orElseThrow( () -> new ResourceNotFoundException("Product not found"));

                CartItem cartItem = new CartItem();

                cartItem.setCart(user.getCart());
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem = cartItemDao.save(cartItem);
                user.getCart().getCartItems().add(cartItem);

                cartDao.save(user.getCart());
                userDao.save(user);
        }

        @Override
        public void deleteCartItem(Long productId, MyUserDetail loggedUser) {

                User user = userDao.findById(loggedUser.getId()).orElse(new User());

                for (CartItem c : user.getCart().getCartItems()) {
                        if (c.getProduct().getId().equals(productId)) {
                                cartItemDao.delete(c);
                                break;
                        }
                }
        }

        @Override
        public void clearCart(MyUserDetail loggedUser) {
                User user = userDao.findById(loggedUser.getId()).orElse(new User());
                cartItemDao.deleteAllInBatch(user.getCart().getCartItems());
        }


        @Override
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

        @Override
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
