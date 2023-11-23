package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Entity.Sizes;
import com.example.sd_95_polo_store_be.Model.Request.ImageRequest;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRepuest;
import com.example.sd_95_polo_store_be.Model.Response.DiscountProductDetailReponse;
import com.example.sd_95_polo_store_be.Model.Response.ProductDetailResponse;
import com.example.sd_95_polo_store_be.Repository.*;
import com.example.sd_95_polo_store_be.Service.ImageService;
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
import java.util.Objects;
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
    @Autowired
    private ImageService imageService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private DiscountRepository discountRepository;

    @Override
    public ArrayList<ProductDetail> getAllProductDetail() {
        return (ArrayList<ProductDetail>) productDetailRepository.findAll();
    }

    @Override
    public ProductDetail saveProductDetail(ProductDetailRepuest request) {
        return null;
    }

    @Override
    public void createOrUpdate(ProductDetailRepuest productDetailRequest, Integer productId) {
        var size = sizeRepository.findById(productDetailRequest.getSizeId()).orElseThrow();
        var color = colorRepository.findById(productDetailRequest.getColorId()).orElseThrow();
        var discount = discountRepository.findById((productDetailRequest.getDiscountId())).orElseThrow();
        var now = OffsetDateTime.now();
        var product = productRepository.findById(productId).orElseThrow();
        if (productDetailRequest.getProductDetailId() == null) {
            ProductDetail productDetail = new ProductDetail();
            productDetail.setCost(productDetailRequest.getCost());
            productDetail.setPrice(productDetailRequest.getPrice());
            productDetail.setColors(color);
            productDetail.setSizes(size);
            productDetail.setDiscount(discount);
            productDetail.setProducts(product);
            productDetail.setStatus(1);
            productDetail.setCreateDate(now);
            productDetail.setUpdatedAt(now);
            productDetail.setQuantity(productDetailRequest.getQuantity());
            productDetailRepository.save(productDetail);
            if (productDetailRequest.getImages() != null) {
                imageService.createOrUpdate(productDetailRequest.getImages(), productDetail.getId());
            }
        } else {
            var updateProductDetail = productDetailRepository.findById(productDetailRequest.getProductDetailId()).orElseThrow();
            updateProductDetail.setStatus(updateProductDetail.getStatus());
            updateProductDetail.setCost(productDetailRequest.getCost());
            updateProductDetail.setPrice(productDetailRequest.getPrice());
            updateProductDetail.setColors(color);
            updateProductDetail.setSizes(size);
            updateProductDetail.setDiscount(discount);
            updateProductDetail.setProducts(product);
            updateProductDetail.setUpdatedAt(now);
            updateProductDetail.setQuantity(productDetailRequest.getQuantity());
            productDetailRepository.save(updateProductDetail);
            if (productDetailRequest.getImages() != null) {
                imageService.createOrUpdate(productDetailRequest.getImages(), updateProductDetail.getId());
            }

        }

    }

//    @Override
//    public void deleteProductDetailById(List<Long> ids) {
//        if (ids != null && !ids.isEmpty()) {
//            for (Long id : ids) {
//                Optional<ProductDetail> ProductDetailOptional = productDetailRepository.findById(id);
//                if (ProductDetailOptional.isPresent()) {
//                    productDetailRepository.deleteById(id);
//                } else {
//                    throw new IllegalArgumentException("Chi tiết sản phẩm với ID " + id + " không tồn tại");
//                }
//            }
//        } else {
//            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
//        }
//    }

    @Override
    public List<ProductDetailResponse> getForProduct(Integer productId) {
        List<ProductDetailResponse> list = productDetailRepository.getByProductId(productId);
        var productDiscount = productDetailRepository.getProductDiscounts();
        for (ProductDetailResponse productDetailResponses : list) {
            productDetailResponses.setImages(imageService.gets(productDetailResponses.getProductDetailId()));
            if (productDetailResponses.getStatus() == 3) {
                for (DiscountProductDetailReponse discountProductDetailReponse : productDiscount) {
                    if (Objects.equals(productDetailResponses.getProductDetailId(), discountProductDetailReponse.getProductDetailId())) {
                        var pricePromotion = calculatePromotion(discountProductDetailReponse.getCost(), discountProductDetailReponse.getDiscount());
                        productDetailResponses.setPromotionPercent((int) (100 - pricePromotion / productDetailResponses.getPricecost() * 100));
                        productDetailResponses.setPrice(pricePromotion);
                    }
                }
            } else {
                productDetailResponses.setPromotionPercent(0);
            }
        }
        return list;
    }

    private Double calculatePromotion(Float price, Float pricePromotion) {
        return Math.floor((price - price * (pricePromotion / 100)) / 1000) * 1000 + 9000;
    }

}
