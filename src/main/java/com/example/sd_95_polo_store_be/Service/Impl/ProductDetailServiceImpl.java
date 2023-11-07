package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;

    @Override
    public ArrayList<ProductDetail> getAllProductDetail() {
        return (ArrayList<ProductDetail>) productDetailRepository.findAll();
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetail productDetail) {
        if (ObjectUtils.isEmpty(productDetail.getQuantity().toString().trim())) {
            throw new IllegalArgumentException("Số lượng không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getCost().toString().trim())) {
            throw new IllegalArgumentException("Cost không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getPrice().toString().trim())) {
            throw new IllegalArgumentException("price không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getProducts().getId().toString().trim())) {
            throw new IllegalArgumentException("Product không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getColors().getId().toString().trim())) {
            throw new IllegalArgumentException("Product không để trống");
        } else if (ObjectUtils.isEmpty(productDetail.getSizes().getId().toString().trim())) {
            throw new IllegalArgumentException("Product không để trống");
        }
            var now = OffsetDateTime.now();
            if (productDetail.getId() != null) {
                Optional<ProductDetail> existingProductDetails = productDetailRepository.findById(productDetail.getId());
                if (existingProductDetails.isPresent()) {
                    ProductDetail updateProductDetails = existingProductDetails.get();
                    updateProductDetails.setQuantity(productDetail.getQuantity());
                    updateProductDetails.setStatus(0);
                    updateProductDetails.setDescription(productDetail.getDescription());
                    updateProductDetails.setCost(productDetail.getCost());
                    updateProductDetails.setPrice(productDetail.getPrice());
                    updateProductDetails.setUpdatedAt(now);
                    updateProductDetails.setProducts(productDetail.getProducts());
                    updateProductDetails.setColors(productDetail.getColors());
                    updateProductDetails.setSizes(productDetail.getSizes());
                    return productDetailRepository.save(productDetail);
                } else {
                    throw new IllegalArgumentException("Không tìm thấy chi tiết sảnb phẩm với id: " + productDetail.getId());
                }
            } else {
                ProductDetail newProductDetail1 = new ProductDetail();
                newProductDetail1.setQuantity(productDetail.getQuantity());
                newProductDetail1.setStatus(0);
                newProductDetail1.setDescription(productDetail.getDescription());
                newProductDetail1.setCost(productDetail.getCost());
                newProductDetail1.setPrice(productDetail.getPrice());
                newProductDetail1.setCreatedAt(now);
                newProductDetail1.setUpdatedAt(now);
                newProductDetail1.setProducts(productDetail.getProducts());
                newProductDetail1.setColors(productDetail.getColors());
                newProductDetail1.setSizes(productDetail.getSizes());
                return productDetailRepository.save(productDetail);
            }
        }



    @Override
    public void deleteProductDetailById(Long id) {

    }
}
