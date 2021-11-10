package com.dsi.ecommerce.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Data
@Getter
@Setter
public class Product {
    @Id
    @SequenceGenerator(name = "product_id_sequence", sequenceName = "product_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", columnDefinition = "TEXT")
    private String image;

    @Column(name = "category", nullable = false, columnDefinition = "TEXT")
    private String category;

    @Column(name = "price", nullable = false, columnDefinition="Decimal(10,2)")
    private Double price;

    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;

    @Column(name = "sold_items", nullable = false)
    private Integer soldItems;

//    @ManyToOne
//    private Shop shop;

//    @OneToMany(mappedBy = "product")
//    private List<Review> reviews;


}
