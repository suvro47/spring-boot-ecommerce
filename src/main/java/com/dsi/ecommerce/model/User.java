package com.dsi.ecommerce.model;

import com.dsi.ecommerce.model.cart.Cart;
import com.dsi.ecommerce.model.order.Order;
import com.dsi.ecommerce.utility.UserRoles;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Table(
        name = "users",
        uniqueConstraints = @UniqueConstraint(name = "username_unique",columnNames = "username")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    @SequenceGenerator(name = "user_id_generator", sequenceName = "user_id_sequence", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "firstname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "profile_pic")
    private String profilePic;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

    @OneToOne( mappedBy="user" )
    private Shop shop;

    @OneToMany( mappedBy="user" )
    private List<Review> reviews;

    @OneToMany( mappedBy="user" )
    private List<ReviewReply> reviewReplies;

    @OneToOne
    private Cart cart;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private UserRoles role;
    @Column(name = "active", nullable = false)
    private boolean active;
}
