package com.dsi.ecommerce.dao;

import com.dsi.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {

    @Query("select u from users u where u.username = ?1")
    User findByUsername(String username);
}
