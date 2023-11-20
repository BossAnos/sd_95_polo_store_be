package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDetailResponse {
    private Integer productDetailId;
    private Integer sizeId;
    private Integer colorId;
    private String nameSize;
    private String nameColor;
    private String quantity;
    private Double cost;
    private Double price;
    private String description;
    List<ImageProductResponse> images;

}
