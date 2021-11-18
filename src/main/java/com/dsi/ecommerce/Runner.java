package com.dsi.ecommerce;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.utility.constants.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @Override
    public void run(String... args) {

        if( userDao.findByUsername("admin").isEmpty() ) {

            User admin = new User();

            admin.setUsername("admin");
            admin.setActive(true);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setFirstname("Super");
            admin.setLastname("Admin");
            admin.setEmail("admin@dsinnovator.com");
            admin.setProfilePic("/images/profile/default.png");
            admin.setRole(UserRoles.ADMIN);
            admin = userDao.save(admin);

            Cart cart = new Cart();
            List<CartItem> list = new ArrayList<CartItem>();
            cart.setCartItems(list);
            cart = cartDao.save(cart);
            admin.setCart(cart);

            userDao.save(admin);
        }
    }
}
