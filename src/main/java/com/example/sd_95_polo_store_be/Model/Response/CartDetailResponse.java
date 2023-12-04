package com.example.sd_95_polo_store_be.Model.Response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CartDetailResponse {
    private Long cartDetailId;
    private Integer productDetailId;
    private String nameProduct;
    private String nameSize;
    private String nameColor;
    private String Image;
    private Integer status;
    private Integer quantity;
    private Integer discountId;
    private Float pricePromotion;
    private Float priceCore;


    public CartDetailResponse(Long idCartDetail, Integer productDetailId, String nameProduct, String nameSize, String nameColor, Integer status, Integer quantity,Integer discountId,Float priceCore) {
        this.cartDetailId = idCartDetail;
        this.productDetailId = productDetailId;
        this.nameProduct = nameProduct;
        this.nameSize = nameSize;
        this.nameColor = nameColor;
        this.status = status;
        this.quantity = quantity;
        this.discountId = discountId;
        this.priceCore = priceCore;
    }
}
