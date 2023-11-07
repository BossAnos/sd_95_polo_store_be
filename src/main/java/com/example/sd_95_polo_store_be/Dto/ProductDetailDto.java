package com.example.sd_95_polo_store_be.Dto;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ProductDetailDto {
    Long id;

    Integer quantity;

    Double cost;

    Double price;

    Integer status;

    String description;
    String nameProduct;
    String nameSize;
    String nameColor;

//    @JsonFormat(pattern = "dd/MM/yyyy")
//    Date create_date;
//
//    @JsonFormat(pattern = "dd/MM/yyyy")
//    Date update_date;

    public ProductDetailDto(ProductDetail productDetail) {
        id = productDetail.getId();
        quantity = productDetail.getQuantity();
        cost = productDetail.getCost();
        price = productDetail.getPrice();
        status = productDetail.getStatus();
        description = productDetail.getDescription();
//        create_date = productDetail.getCreate_date();
//        update_date = productDetail.getUpdate_date();
        Products products = productDetail.getProducts();
        if (products != null) {
            id = products.getId();
            nameProduct = products.getName();
        }
        Sizes sizes = productDetail.getSizes();
        if (sizes != null) {
            id = products.getId();
            nameSize = sizes.getName();
        }
        Colors colors = productDetail.getColors();
        if (colors != null) {
            id = products.getId();
            nameColor = colors.getName();
        }

    }
}
