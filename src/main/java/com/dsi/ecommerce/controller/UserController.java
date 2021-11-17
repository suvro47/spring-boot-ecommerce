package com.dsi.ecommerce.controller;


import com.dsi.ecommerce.exception.UserNotFoundException;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.MyUserDetail;
import com.dsi.ecommerce.service.UserService;
import com.dsi.ecommerce.utility.constants.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@RequestMapping("/users")
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

    public String getUserProfile(@PathVariable("username") String username, Model model,
                                 @AuthenticationPrincipal MyUserDetail currentUser){

        if(Objects.equals(username, currentUser.getUsername()) || currentUser.getAuthorities().contains(new SimpleGrantedAuthority(UserRoles.ADMIN.toString())) ){
            try {
                User user = userService.getUserByUsername(username);
                model.addAttribute("user", user);
            }
            catch (UserNotFoundException exception){
                exception.printStackTrace();
                //todo: handle UserNotFound
            }

            return "user_profile";
        }else  return "redirect:/users/" + currentUser.getUsername();
    }

    @RequestMapping(value = "/{username}/edit", method = RequestMethod.GET)
    public String updateUserForm(@PathVariable("username") String username, Model model,
                                 @AuthenticationPrincipal MyUserDetail currentUser){
        if(!Objects.equals(username, currentUser.getUsername())  ) {
            return "redirect:/users/" + currentUser.getUsername() + "/edit";
        }

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
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal MyUserDetail currentUser
    ){
        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute(model);
            return "user_profile";
        }
        if(!Objects.equals(username, currentUser.getUsername())  ) {
            return "redirect:/users/" + currentUser.getUsername() + "/edit";
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
    public String deleteUser(@PathVariable("username") String username, Model model,@AuthenticationPrincipal MyUserDetail currentUser){
        if(!Objects.equals(username, currentUser.getUsername()) && !currentUser.getAuthorities().contains(new SimpleGrantedAuthority(UserRoles.ADMIN.toString())) ) {
            return "redirect:/";
        }
        try{
            userService.deleteUserByUsername(username);
        }catch (UserNotFoundException exception){
            exception.printStackTrace();
            //todo: handle UserNotFound
        }
        return "redirect:/";
    }
}
