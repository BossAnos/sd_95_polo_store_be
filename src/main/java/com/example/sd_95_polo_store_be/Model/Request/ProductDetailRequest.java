package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;

@Data
public class ProductDetailRequest {
    Long id;
    Integer quantity;
    Double cost;
    Double price;
    Integer status;
    String description;
    Integer productId;
    Integer sizeId;
    Long colorId;
}
