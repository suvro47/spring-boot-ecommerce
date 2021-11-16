package com.dsi.ecommerce;

import com.dsi.ecommerce.dao.ProductDao;
import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.dao.cart.CartDao;
import com.dsi.ecommerce.dao.cart.CartItemDao;
import com.dsi.ecommerce.model.Product;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.cart.CartItem;
import com.dsi.ecommerce.utility.UserRoles;
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
    private ProductDao productDao;

    @Autowired
    private CartItemDao cartItemDao;

    @Autowired
    private CartDao cartDao;

    @Override
    public void run(String... args) throws Exception {
        if(userDao.findByUsername("admin") == null){
            User admin = new User();

            admin.setUsername("admin");
            admin.setActive(true);
            admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
            admin.setFirstname("Super");
            admin.setLastname("Admin");
            admin.setEmail("admin@dsinnovator.com");
            admin.setProfilePic("/images/profile/default.png");
            admin.setRole(UserRoles.ADMIN);

            //
            admin = userDao.save(admin);

            Product product = new Product();
            product.setCategory("grocery");
            product.setDescription("description");
            product.setPrice(567.5);
            product.setName("Smart watch");
            product.setSoldItems(3);
            product.setAvailableQuantity(70);
            product = productDao.save(product);

            Product product2 = new Product();
            product2.setCategory("Electronics");
            product2.setDescription("iPhone 13 Pro mobile in Bangladesh.");
            product2.setPrice(147999.);
            product2.setName("iPhone 13 Pro");
            product2.setSoldItems(1);
            product2.setAvailableQuantity(9);
            product2 = productDao.save(product2);

            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(5);

            CartItem cartItem2 = new CartItem();
            cartItem2.setProduct(product2);
            cartItem2.setQuantity(1);

            Cart cart = new Cart();

            List<CartItem> list = new ArrayList<CartItem>();
            list.add(cartItem);
            list.add(cartItem2);

            cart.setCartItems(list);
            cart = cartDao.save(cart);
            cartItem.setCart(cart);
            cartItem2.setCart(cart);

            cartItemDao.save(cartItem);

            admin.setCart(cart);


            cartItemDao.save(cartItem2);

            cartDao.save(cart);
            //

            userDao.save(admin);
        }
    }
}
