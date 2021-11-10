package com.dsi.ecommerce.dao.cart;

import com.dsi.ecommerce.model.cart.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemDao extends JpaRepository<CartItem, Long> {
}
