package com.dsi.ecommerce.service.impl;

import com.dsi.ecommerce.dao.ShopDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.exception.ResourceAlreadyExists;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.MyUserDetail;
import com.dsi.ecommerce.service.ShopService;
import com.dsi.ecommerce.utility.FileUpload;
import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopDao shopDao;

    @Autowired
    private UserDao userDao;

    @Override
    public Shop saveShop(MyUserDetail principal, ShopDto shopDetails, MultipartFile file) throws ResourceNotFoundException, ResourceAlreadyExists {

        Shop shop = shopDao.findShopByUserId(principal.getId());
        if( shop != null ) throw new ResourceAlreadyExists("Shop already exist");

        Shop newShop = new Shop();
        newShop.setName(shopDetails.getName());
        newShop.setDescription(shopDetails.getDescription());
        newShop.setRegisteringDate( new Date() );
        User loggedUser = userDao.findById(principal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        newShop.setUser(loggedUser);
        newShop.setBanner(FileUpload.saveImage(ImageType.SHOP_BANNER, shopDetails.getName(), file));
        return shopDao.save(newShop);
    }

    @Override
    public Shop getShop(MyUserDetail principal) throws ResourceNotFoundException {

        User loggedUser = userDao.getById(principal.getId());
        Shop shop = loggedUser.getShop();
        if( shop.getId() == null ) throw new ResourceNotFoundException("Shop Not found");
        return shop;
    }

    @Override
    public Shop updateShop(MyUserDetail principal, Long id, ShopDto shopDetails, MultipartFile file, MultipartFile file2)
            throws ResourceNotFoundException {

        Shop shop = shopDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
        shop.setName(shopDetails.getName());
        shop.setDescription(shopDetails.getDescription());
        shop.setBanner(FileUpload.saveImage(ImageType.SHOP_BANNER, shopDetails.getName(), file));
        shop.setAdvertisingBanner(FileUpload.saveImage(ImageType.ADVERTISING_BANNER, shopDetails.getName(), file2));
        return shopDao.save(shop);
    }

    @Override
    public void deleteAdvertisingBanner(MyUserDetail principal, Long id) throws ResourceNotFoundException {
        Shop shop = shopDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Shop not found"));
        shop.setAdvertisingBanner(null);
        shopDao.save(shop);
    }


}
