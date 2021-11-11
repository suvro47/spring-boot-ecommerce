package com.dsi.ecommerce.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
public class ProductDTO {
    private String name;
    private String description;
    private String image;
    private String category;
    private Double price;
    private Integer availableQuantity;
}
