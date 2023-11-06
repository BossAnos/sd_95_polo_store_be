package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface ProductDetailService {
    ArrayList<ProductDetail> getAll();
    void saveProductDetail(ProductDetail productDetail);

    void update(ProductDetail productDetail, Long id);

    void deleteProductDetailById(Long id);
}
