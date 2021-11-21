package com.dsi.ecommerce.service;

import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.cart.CartItem;

import java.util.List;

public interface CartRestService {

    List<CartItem> getAllCartItem(MyUserDetail loggeduser);

    void addCartItem(long productId, Integer quantity, MyUserDetail logoedUser) throws ResourceNotFoundException;

    void deleteCartItem(Long productId, MyUserDetail loggedUser);

    void clearCart(MyUserDetail loggedUser);

    void increaseQuantity(Long productId, MyUserDetail principal);

    void decreaseQuantity(Long productId, MyUserDetail principal);


}
