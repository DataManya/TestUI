package ru.seleniumtest.dto;

import lombok.Data;

@Data
public class ProductParams {
    private String id;
    private String categoryId;
    private Integer price;
    private Integer oldPrice;
    private String shortName;
    private String categoryName;
    private String brandName;
    private Integer clubPrice;
}
