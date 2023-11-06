package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;
import com.example.sd_95_polo_store_be.Model.Response.BrandResponse;

import java.util.List;

public interface BrandService {
    List<Brands> gets();

    Brands createOrUpdate(BrandRequest request);

    boolean isBrand(Brands brands);

    void deleteBrandByIds(List<Integer> ids);
}
