package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.model.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {

    Shop saveShop(ShopDto shopDetails, MultipartFile file);

}
