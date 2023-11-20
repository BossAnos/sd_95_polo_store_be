package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRequest;
import com.example.sd_95_polo_store_be.Repository.ColorRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Repository.SizeRepository;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import org.hibernate.engine.jdbc.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ColorRepository colorRepository;

    @Override
    public ArrayList<ProductDetail> getAllProductDetail() {
        return (ArrayList<ProductDetail>) productDetailRepository.findAll();
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetailRequest request) {
        if (ObjectUtils.isEmpty(request.getQuantity().toString().trim())) {
            throw new IllegalArgumentException("Số lượng không để trống");
        } else if (ObjectUtils.isEmpty(request.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(request.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (ObjectUtils.isEmpty(request.getCost().toString().trim())) {
            throw new IllegalArgumentException("Cost không để trống");
        } else if (ObjectUtils.isEmpty(request.getPrice().toString().trim())) {
            throw new IllegalArgumentException("price không để trống");
        } else if (request.getColorId() == null) {
            throw new IllegalArgumentException("Mã màu sắc không được để trống");
        } else if (request.getSizeId() == null) {
            throw new IllegalArgumentException("Mã size không để trống");
        } else if (request.getProductId() == null) {
            throw new IllegalArgumentException("Mã sản phẩm không để trống");
        }
        Products products = productRepository.findById(request.getProductId()).orElse(null);
        Sizes size = sizeRepository.findById(request.getSizeId()).orElse(null);
        Colors colors = colorRepository.findById(request.getColorId()).orElse(null);
        var now = OffsetDateTime.now();
        if (request.getId() != null) {
            Optional<ProductDetail> existingProductDetails = productDetailRepository.findById(request.getId());

            if (existingProductDetails.isPresent()) {
                ProductDetail updateProductDetails = existingProductDetails.get();
                updateProductDetails.setQuantity(request.getQuantity());
                updateProductDetails.setStatus(1);

                updateProductDetails.setCost(request.getCost());
                updateProductDetails.setPrice(request.getPrice());
                updateProductDetails.setUpdatedAt(now);
                updateProductDetails.setProducts(products);
                updateProductDetails.setColors(colors);
                updateProductDetails.setSizes(size);
                return productDetailRepository.save(updateProductDetails);
            } else {
                throw new IllegalArgumentException("Không tìm thấy chi tiết sảnb phẩm với id: " + request.getId());
            }
        } else {
            ProductDetail newProductDetail1 = new ProductDetail();
            newProductDetail1.setQuantity(request.getQuantity());
            newProductDetail1.setStatus(1);
            newProductDetail1.setCost(request.getCost());
            newProductDetail1.setPrice(request.getPrice());
            newProductDetail1.setCreatedAt(now);
            newProductDetail1.setUpdatedAt(now);
            newProductDetail1.setProducts(products);
            newProductDetail1.setColors(colors);
            newProductDetail1.setSizes(size);
            return productDetailRepository.save(newProductDetail1);
        }
    }

    @Override
    public void deleteProductDetailById(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                Optional<ProductDetail> ProductDetailOptional = productDetailRepository.findById(id);
                if (ProductDetailOptional.isPresent()) {
                    productDetailRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Chi tiết sản phẩm với ID " + id + " không tồn tại");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }
    }


}
