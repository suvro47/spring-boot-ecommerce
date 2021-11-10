package com.dsi.ecommerce.dao;

import com.dsi.ecommerce.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemDao extends JpaRepository<CartItem, Long> {
}
