package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.common.Mapper.EntityMapper;

public interface ProductService {
    void addProduct(ProductRequset productRequset);

}
