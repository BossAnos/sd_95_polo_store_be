package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDetailResponse {
    private Integer productDetailId;
    private Integer sizeId;
    private Long colorId;
    private String nameSize;
    private String nameColor;
    private Integer quantity;
    private Float cost;
    private Float price;
    private Integer status;
    private Integer promotionPercent;
    private Float pricecost;

    List<ImageProductResponse> images;

    public ProductDetailResponse(Integer productDetailId, Integer sizeId, Long colorId, String nameSize, String nameColor, Integer quantity, Float cost, Float price, Integer status) {
        this.productDetailId = productDetailId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.nameSize = nameSize;
        this.nameColor = nameColor;
        this.quantity = quantity;
        this.cost = cost;
        this.price = price;
        this.status = status;
    }
}
