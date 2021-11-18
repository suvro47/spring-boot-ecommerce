package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.dto.ProductDTO;
import com.dsi.ecommerce.exception.ResourceNotFoundException;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.Shop;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.MyUserDetail;
import com.dsi.ecommerce.service.ProductService;
import com.dsi.ecommerce.service.ShopService;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import com.dsi.ecommerce.utility.FileUpload;
import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private CartServiceImpl cartService;

    @RequestMapping("/products")

    public String getAllProducts( Model model ) {

        List<Product> products = productService.getProducts();
        model.addAttribute("products", products);

        List<CartItem> cartItemList = new ArrayList<>();
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost",0.0);

        return "product/products";
    }



    @RequestMapping("/user/shop/{id}/product/{id2}")
    public String getProduct(@AuthenticationPrincipal MyUserDetail principal,Model model, @PathVariable(value="id") Long shopId , @PathVariable(value="id2") Long productId ) {

        List<CartItem> cartItemList = cartService.getAllCartItem(principal);
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost", cartService.getTotalCost());

        try {
            Product product = productService.getProduct(shopId, productId);
            model.addAttribute("product", product);
            System.out.println(product.getName());
        } catch (Exception e) {
            System.out.println("Exception Occur : " + e );
        }
        return "product/product";
    }


    @RequestMapping(value = "/seller/shop/{shop_id}/add-product", method = RequestMethod.GET)
    public String getProductAddForm(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="shop_id") Long shopID, Model model) {

        try {
            List<CartItem> cartItemList = cartService.getAllCartItem(principal);
            model.addAttribute("cartItems", cartItemList);
            model.addAttribute("totalCost", cartService.getTotalCost());

            Shop shop = shopService.getShop(principal);
            if (shop.getId() == shopID){
                ProductDTO productDTO = new ProductDTO();
                model.addAttribute("product", productDTO);
                model.addAttribute("shop_id", shopID);
                return "product/products_add_form";
            }
            else {
                System.out.println("-----------------Permission Denied!----------------");
                return "index";
            }
        } catch (Exception e) {
            System.out.println("An exception occured ");
            return "index";
        }
    }

    @RequestMapping(value = "/seller/shop/{shop_id}/add-product", method = RequestMethod.POST)
    public String addProduct(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="shop_id") Long shopID,
                             @ModelAttribute("product") ProductDTO productDTO, @RequestParam(value = "image") MultipartFile image,
                             Model model) throws ResourceNotFoundException {
        System.out.println("image---: "+ image);
        if(image.isEmpty()){
            System.out.println("File mot uploaded");
        }
        else{
            System.out.println("Image Name:--------"+image.getOriginalFilename());
        }
//        Shop shop = shopService.getShop(principal);
//        Product product = productService.convertProductDTOtoProductEntity(new Product(), productDTO, shop, image);
//        productService.saveProduct(product);
//        List<CartItem> cartItemList = cartService.getAllCartItem(principal);
//        model.addAttribute("cartItems", cartItemList);
//        model.addAttribute("totalCost", cartService.getTotalCost());
        return "redirect:/seller/my_shop";
    }

    @RequestMapping(value = "/seller/shop/{shop_id}/edit-product/{product_id}", method = RequestMethod.GET)
    public String getProductEditForm(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="product_id") Long productID, @PathVariable(value="shop_id") Long shopID, Model model) {

        List<CartItem> cartItemList = cartService.getAllCartItem(principal);
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost", cartService.getTotalCost());

        try {
            Shop shop = shopService.getShop(principal);
            Product product = productService.getProduct(shopID, productID);
            if (shop.getId() == shopID){
                ProductDTO productDTO = productService.convertsProductEntityToProductDTO(product);
                model.addAttribute("product", productDTO);
                model.addAttribute("shop_id", shopID);
                model.addAttribute("product_id", productID);
                model.addAttribute("image",product.getImage());
                return "product/products_edit_form";
//                return "index";
            }
            else {
                System.out.println("-----------------Permission Denied!----------------");
                return "index";
            }
        } catch (Exception e) {
            System.out.println("An exception occured ");
            return "index";
        }
    }


    @RequestMapping(value = "/shop/{shop_id}/edit-product/{product_id}", method = RequestMethod.POST)
    public String editProduct(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="product_id") Long productID, @PathVariable(value="shop_id") Long shopID, @ModelAttribute("product") ProductDTO productDTO, @RequestParam(value = "image") MultipartFile image, Model model) throws ResourceNotFoundException {

//        List<CartItem> cartItemList = cartService.getAllCartItem(principal);
//        model.addAttribute("cartItems", cartItemList);
//        model.addAttribute("totalCost", cartService.getTotalCost());

        System.out.println("image---: "+ image);
        if(image.isEmpty()){
            System.out.println("File mot uploaded");
        }
        else{
            System.out.println("Image Name:--------"+image.getOriginalFilename());
        }

//        try{
//            Shop shop = shopService.getShop(principal);
//            Product oldProduct = productService.getProduct(shopID, productID);
//            System.out.println("Method");
//            //String imageName = (image.isEmpty())?oldProduct.getImage(): FileUpload.saveImage(ImageType.PRODUCT_IMAGE, productDTO.getName(), image);
//            //Product product = productService.convertProductDTOtoProductEntity(oldProduct, productDTO, shop, imageName);
//            //productService.saveProduct(product);
//        }
//        catch (Exception e){
//            System.out.println("An exception occured.");
//        }

        return "index";
    }


    @RequestMapping(value = "/shop/{shop_id}/delete-product/{product_id}", method = RequestMethod.GET)
    public String deleteProduct(@AuthenticationPrincipal MyUserDetail principal, @PathVariable(value="product_id") Long productID, @PathVariable(value="shop_id") Long shopID, Model model) throws ResourceNotFoundException {
        List<CartItem> cartItemList = cartService.getAllCartItem(principal);
        model.addAttribute("cartItems", cartItemList);
        model.addAttribute("totalCost", cartService.getTotalCost());

        try{
            Shop shop = shopService.getShop(principal);
            Product product = productService.getProduct(shopID, productID);
            if (shop.getId() == shopID) {
                productService.deleteProduct(product);
            }
            else {
                System.out.println("-----------------Permission Denied!----------------");
            }
            return "index";
        }
        catch (Exception e){
            System.out.println("An exception occured.");
        }
        return "index";
    }


}
