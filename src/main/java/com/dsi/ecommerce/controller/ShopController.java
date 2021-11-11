package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ShopDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ShopController {

    @RequestMapping(value="/register_shop", method= RequestMethod.GET)
    public String registerPage(Model model) {
        ShopDto shop = new ShopDto();
        model.addAttribute("shop", shop);
        return "shop/shop_form";
    }

    @RequestMapping(value="/register_shop", method= RequestMethod.POST)
    public String registerSubmit(ShopDto shopDetails, @RequestParam(value = "file") MultipartFile file) {



        return "shop/shop_form";
    }


}
