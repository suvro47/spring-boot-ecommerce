package com.dsi.ecommerce.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class ShopDto {

    private String name;

    private Date registeringDate;

    private Double rating;

    private String poster;

    private String description;

    private Boolean isVarified;

}
