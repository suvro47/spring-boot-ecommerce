package com.dsi.ecommerce.model;


import com.dsi.ecommerce.model.cart.Cart;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "user")
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
    @Column(name = "role", nullable = false)
    private String role;

    @OneToOne
    private Cart cart;
}
