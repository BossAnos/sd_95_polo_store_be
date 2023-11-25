package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Repository.DiscountRepository;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public List<DiscountResponse> gets() {
        return discountRepository.gets();
    }

    @Override
    public void expireDiscounts() {
        discountRepository.expireActiveDiscounts();
    }
}
