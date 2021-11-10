package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ShopDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopController {

    @RequestMapping(value="/register_shop", method= RequestMethod.GET)
    public String registerNewShop(Model model) {
        ShopDto shop = new ShopDto();
        model.addAttribute("shop", shop);
        return "shop/shop_form";
    }


}
