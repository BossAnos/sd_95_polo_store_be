package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ProductDetailService {
    ArrayList<ProductDetail> getAllProductDetail();
    ProductDetail saveProductDetail(ProductDetailRequest request);

    void deleteProductDetailById(List<Long> ids);

}
