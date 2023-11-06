package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ArrayList<ProductDetail> getAllProductDetail() {
        return (ArrayList<ProductDetail>) productDetailRepository.findAll();
    }

    @Override
    public void saveProductDetail(ProductDetail productDetail) {

    }

    @Override
    public void update(ProductDetail productDetail, Long id) {

    }

    @Override
    public void deleteProductDetailById(Long id) {

    }
}
