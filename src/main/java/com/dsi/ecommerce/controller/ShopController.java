package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ShopDto;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String registerSubmit(@AuthenticationPrincipal MyUserDetail principal, Model model, ShopDto shopDetails, MultipartFile file) {

        try {
            shopService.saveShop(principal, shopDetails, file);
            return "redirect:/my_shop";

        } catch( Exception e ) {
            System.out.println("Exception has occured " + e );
            return "shop/shop_form";
        }
    }

    @RequestMapping(value="/my_shop", method= RequestMethod.GET)
    public String getShop(@AuthenticationPrincipal MyUserDetail principal, Model model ) {

        try {
            Shop shop = shopService.getShop( principal );
            model.addAttribute("shop", shop);
            return "shop/shop_view";

        } catch ( Exception e ) {
            System.out.println("An exception occured ");
            return "shop/shop_form";
        }
    }

    @RequestMapping(value="/update_shop/{id}", method= RequestMethod.GET)
    public String setUpdateShopDetails(@AuthenticationPrincipal MyUserDetail principal, @PathVariable Long id, Model model ) {

        try {
            Shop shop = shopService.getShop(principal);
            model.addAttribute("shop", shop);
            return "shop/edit_shop";

        } catch( Exception e ) {
            System.out.println("Exception has occured " + e );
            return "shop/shop_view";
        }
    }

    @RequestMapping(value="/update_shop/{id}", method= RequestMethod.POST)
    public String updateSubmit(@AuthenticationPrincipal MyUserDetail principal, Model model,@PathVariable Long id, ShopDto shopDetails, MultipartFile file) {

        try {
            shopService.updateShop(principal, id, shopDetails, file);
            return "redirect:/my_shop";

        } catch( Exception e ) {
            System.out.println("Exception has occured " + e );
            return "shop/shop_view";
        }
    }







}
