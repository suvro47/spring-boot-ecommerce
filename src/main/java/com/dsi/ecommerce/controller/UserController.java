package com.dsi.ecommerce.controller;


import com.dsi.ecommerce.exception.UserNotFoundException;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping
    public String getAllUser(Model model){
        try {
            List<User> users = userService.getAllUsers();
            model.addAttribute("users",users);
        }catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }
        return "all_user";
    }

    @RequestMapping("/{username}")
    public String getUserProfile(@PathVariable("username") String username, Model model){
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
        }
        catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }

        return "user_profile";
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String updateUserForm(@PathVariable("username") String username, Model model){
        try {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
        }
        catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }

        return "user_profile";
    }

    @RequestMapping(value = "/{username}/edit",method = RequestMethod.POST)
    public String updateUser(
            @PathVariable("username") String username,
            User user,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes
    ){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(model);
            return "user_profile";
        }
        try{
            User updatedUser = userService.updateUser(username, user);
            model.addAttribute("user", updatedUser);
        }catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }
        return "user_profile";
    }

    @RequestMapping(value = "/{username}/delete",method = RequestMethod.GET)
    public String deleteUser(@PathVariable("username") String username, Model model){
        try{
            userService.deleteUserByUsername(username);
        }catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }
        return "redirect:/users";
    }
}
