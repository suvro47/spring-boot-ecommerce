package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ShopDao;
import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.ShopService;
import com.dsi.ecommerce.utility.FileUpload;
import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    private ShopDao shopDao;

    @Autowired
    public ShopServiceImpl(ShopDao shopDao) {
        this.shopDao = shopDao;
    }

    @Override
    public Shop saveShop(ShopDto shopDetails, MultipartFile file) {
        Shop shop = new Shop();
        shop.setName(shopDetails.getName());
        shop.setDescription(shopDetails.getDescription());
        shop.setRegisteringDate( new Date() );
//      shop.setUser(new User());  // need to change
        Shop savedShop = shopDao.save(shop);
        savedShop.setPoster(FileUpload.saveImage(ImageType.SHOP_POSTER, savedShop.getName(), file));
        return savedShop;
    }
}
