package com.dsi.ecommerce.service;

import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.exception.ResourceAlreadyExists;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Shop;
import org.springframework.web.multipart.MultipartFile;

public interface ShopService {

    Shop saveShop(MyUserDetail principal, ShopDto shopDetails, MultipartFile file) throws ResourceNotFoundException, ResourceAlreadyExists;

    Shop getShop(MyUserDetail principal) throws ResourceNotFoundException;

    Shop updateShop(MyUserDetail principal, Long id, ShopDto shopDetails, MultipartFile file, MultipartFile file2 ) throws ResourceNotFoundException;

    void deleteAdvertisingBanner(MyUserDetail principal, Long id) throws ResourceNotFoundException;
}
