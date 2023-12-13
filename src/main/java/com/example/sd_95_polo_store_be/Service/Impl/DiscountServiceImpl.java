package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Repository.DiscountRepository;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<DiscountResponse> gets() {
        expireDiscounts();
        productRepository.updateProductsForExpiredDiscounts();
        return discountRepository.gets();
    }

    @Override
    public void expireDiscounts() {
        discountRepository.expireActiveDiscounts();
    }
}
