package com.dsi.ecommerce.dao.cart;

import com.dsi.ecommerce.model.cart.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends JpaRepository<Cart, Long> {

}
