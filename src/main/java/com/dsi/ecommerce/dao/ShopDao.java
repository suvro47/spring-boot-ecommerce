package com.dsi.ecommerce.dao;

import com.dsi.ecommerce.model.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDao extends JpaRepository<Shop, Long> {

    @Query(value = "SELECT shops.* FROM shops WHERE user_id =?1", nativeQuery = true)
    Shop findShopByUserId(long id);

}
