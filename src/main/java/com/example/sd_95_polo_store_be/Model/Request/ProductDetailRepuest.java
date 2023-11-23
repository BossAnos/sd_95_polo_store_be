package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductDetailRepuest {
    Integer idProductDetail;
    private Long colorId;
    private Integer quantity;
    private Integer sizeId;
    private Integer discountId;
    private Float price;
    private Float cost;
    private List<ImageRequest> images;
}
