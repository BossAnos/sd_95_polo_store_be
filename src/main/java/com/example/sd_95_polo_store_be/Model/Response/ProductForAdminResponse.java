package com.example.sd_95_polo_store_be.Model.Response;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import lombok.Data;
import lombok.experimental.Accessors;

import java.awt.*;
import java.util.List;

@Data
@Accessors(chain = true)
public class ProductForAdminResponse {
    private Long id;
    private String name;
    private Integer status;
    private String description;
    private Long categoryId;
    private Integer brandId;
    private Integer materialId;
    private String nameBrand;
    private String nameCategory;
    private String nameMaterial;
    List<ProductDetailResponse> listProductDetail;
    private String image;

    public ProductForAdminResponse(Long id, String name, Integer status, String description, Long categoryId, Integer brandId, Integer materialId, String nameBrand, String nameCategory, String nameMaterial) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.categoryId = categoryId;
        this.brandId = brandId;
        this.materialId = materialId;
        this.nameBrand = nameBrand;
        this.nameCategory = nameCategory;
        this.nameMaterial = nameMaterial;
    }
}
