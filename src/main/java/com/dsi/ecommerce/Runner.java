package com.dsi.ecommerce;

import com.dsi.ecommerce.dao.UserDao;
import com.dsi.ecommerce.model.User;
import com.dsi.ecommerce.utility.UserRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

    @Autowired
    private UserDao userDao;

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

            userDao.save(admin);
        }
    }
}
