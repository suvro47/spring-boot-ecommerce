package com.dsi.ecommerce.dao;

import com.dsi.ecommerce.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDao extends JpaRepository<Cart, Long> {
}
