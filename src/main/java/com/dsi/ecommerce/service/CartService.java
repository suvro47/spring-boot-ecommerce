package com.dsi.ecommerce.service;

import com.dsi.ecommerce.model.cart.CartItem;
import java.util.List;

public interface CartService {

        List<CartItem> getAllCartItem(MyUserDetail loggedUser);

        void addCartItem(Long id);

        void deleteCartItem(Long id);

        void increaseQuanity(Long id);

        void decreaseQuanity(Long id);

        void clearCart();

        Double getTotalCost();
}
