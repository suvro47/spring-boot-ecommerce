package com.dsi.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table( name="shops" )
@Getter
@Setter
public class Shop {

    @Id
    @SequenceGenerator(name = "shop_id_sequence", sequenceName = "shop_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shop_id_sequence")
    @Column(name = "id", updatable = false)
    private long id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date registeringDate;

    @Column(name = "rating", nullable = true, columnDefinition = "Decimal(10,2)")
    private Double rating;

    @Column(name = "poster", nullable = true, columnDefinition = "TEXT")
    private String poster;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_varified", columnDefinition = "boolean default false")
    private Boolean isVarified;

//    @OneToOne(fetch=FetchType.LAZY)
//    private User user;

    @OneToMany(mappedBy = "shop")
    private List<Product> products;








}
