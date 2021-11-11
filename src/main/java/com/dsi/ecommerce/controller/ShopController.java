package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.exception.ResourceAlreadyExists;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value="/register_shop", method= RequestMethod.GET)
    public String registerPage(Model model) {
        ShopDto shop = new ShopDto();
        model.addAttribute("new_shop", shop);
        return "shop/shop_form";
    }

    @RequestMapping(value="/register_shop", method= RequestMethod.POST)
    public String registerSubmit(Model model, ShopDto shopDetails, MultipartFile file) {

        try {
            final Shop savedShop = shopService.saveShop(shopDetails, file);
            model.addAttribute("shop", savedShop);
            return "shop/shop_view";

        } catch( Exception e ) {
            System.out.println("An exception occured ");
            return "shop/shop_form";
        }


    }


}
