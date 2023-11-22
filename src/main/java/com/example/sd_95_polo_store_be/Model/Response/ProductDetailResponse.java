package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDetailResponse {
    private Long productDetailId;
    private Integer sizeId;
    private Long colorId;
    private Integer discountId;
    private String namediscount;
    private Float discount;
    private String nameSize;
    private String nameColor;
    private Integer quantity;
    private Float cost;
    private Float pricecost;
    private Double price;
    private Integer status;
    private Integer promotionPercent;

    List<ImageProductResponse> images;

    public ProductDetailResponse(Long productDetailId, Integer sizeId, Long colorId, Integer discountId, String namediscount, Float discount, String nameSize, String nameColor, Integer quantity, Float cost, Float pricecost, Integer status) {
        this.productDetailId = productDetailId;
        this.sizeId = sizeId;
        this.colorId = colorId;
        this.discountId = discountId;
        this.namediscount = namediscount;
        this.discount = discount;
        this.nameSize = nameSize;
        this.nameColor = nameColor;
        this.quantity = quantity;
        this.cost = cost;
        this.pricecost = pricecost;
        this.status = status;
    }
}
