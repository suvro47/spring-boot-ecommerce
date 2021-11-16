package com.dsi.ecommerce.controller;

import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.service.UserService;
import com.dsi.ecommerce.service.impl.CartServiceImpl;
import com.dsi.ecommerce.utility.FileUpload;
import com.dsi.ecommerce.utility.constants.ImageType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

        @Autowired
        private UserService userService;
        @Autowired
        private CartServiceImpl cartService;

        @GetMapping("/signup")
        public String signupForm(WebRequest request, Model model) {
                model.addAttribute("signup_form", new User());

                List<CartItem> cartItemList = new ArrayList<CartItem>();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", 0.0);
                return "auth/signup";
        }


        @PostMapping("/signup")
        public String signup(User user, BindingResult result,
                             @RequestParam(value = "profilePic") MultipartFile profilePic, RedirectAttributes redirectAttributes, Model model) {

                System.out.println("Hellooo");
                System.out.println(profilePic.getName());
                System.out.println(user.toString());


                try {
                        String path = FileUpload.saveImage(ImageType.USER_PROFILE, user.getUsername(), profilePic);
                        user.setProfilePic(path);
                        userService.createNewUser(user);
                        return "redirect:/";
                } catch (Exception e) {
                        System.out.println(e.toString());

                        List<CartItem> cartItemList = new ArrayList<CartItem>();
                        model.addAttribute("cartItems", cartItemList);
                        model.addAttribute("totalCost", 0.0);

                        redirectAttributes.addFlashAttribute("signup_form", user);

                        return "auth/signup";
                }
        }

        @GetMapping("/login")
        public String loginForm(Model model) {

                List<CartItem> cartItemList = new ArrayList<CartItem>();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost",0.0);
                return "auth/login";
        }

        @RequestMapping("/login-failed")
        public String loginError(Model model) {
                model.addAttribute("loginError", true);
                List<CartItem> cartItemList = new ArrayList<CartItem>();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", 0.0);
                return "auth/login";
        }

        @RequestMapping("/logout-success")
        public String logoutSuccess(Model model) {
                List<CartItem> cartItemList = new ArrayList<CartItem>();
                model.addAttribute("cartItems", cartItemList);
                model.addAttribute("totalCost", 0.0);
                return "redirect:/login";
        }
}
