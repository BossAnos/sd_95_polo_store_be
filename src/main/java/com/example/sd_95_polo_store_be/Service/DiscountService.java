package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.AddDiscountToProductRequest;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;

import java.util.List;

public interface DiscountService {
    List<DiscountResponse> gets();
    void expireDiscounts();

    void addDiscount(AddDiscountToProductRequest addDiscountToProductRequest);
}
