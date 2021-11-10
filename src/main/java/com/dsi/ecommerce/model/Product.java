package com.dsi.ecommerce.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table( name="products" )
@Data
public class Product {

    @Id
    @SequenceGenerator(name = "shop_id_sequence", sequenceName = "shop_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", nullable = true, columnDefinition = "TEXT")
    private String image;

    @Column(name = "category", nullable = false, columnDefinition = "TEXT")
    private String category;

    @Column(name = "price", nullable = false, columnDefinition = "Double")
    private Double price;

    @Column(name = "available_quantity", nullable = false, columnDefinition = "Integer")
    private Integer availableQuantity;

    @Column(name = "sold_items", nullable = false, columnDefinition = "Integer")
    private Integer soldItems;

    @ManyToOne
    private Shop shop;

//    @OneToMany(mappedBy = "product")
//    private List<Review> reviews;


}
