package com.dsi.ecommerce.dao;

import com.dsi.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDao extends JpaRepository<Product,Long> {

}
