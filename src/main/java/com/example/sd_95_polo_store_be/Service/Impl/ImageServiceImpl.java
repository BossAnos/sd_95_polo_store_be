package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ImageRequest;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import com.example.sd_95_polo_store_be.Repository.ImageRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ImageProductResponse> gets(Integer id) {
        return imageRepository.findByProductDetail(id);
    }

    @Override
    public void createOrUpdate(List<ImageRequest> images, Integer productDetailId) {
        var now = OffsetDateTime.now();
        var productDetail = productDetailRepository.findById(productDetailId).orElseThrow();
        for (int i = 0; i < images.size(); i++) {
            if (images.get(i).getId() == null) {
                Images newImage = new Images();
                newImage.setName(images.get(i).getName());
                newImage.setUrl_image(String.valueOf(i));
                newImage.setStatus(1);
                newImage.setCreatedAt(now);
                newImage.setUpdatedAt(now);
                newImage.setProductDetail(productDetail);
                imageRepository.save(newImage);
            } else {
                var imageUpdate = imageRepository.findById(images.get(i).getId()).orElseThrow();
                imageUpdate.setName(images.get(i).getName());
                imageUpdate.setUrl_image(String.valueOf(i));
                imageUpdate.setStatus(1);
                imageUpdate.setUpdatedAt(now);
                imageUpdate.setProductDetail(productDetail);
                imageRepository.save(imageUpdate);
            }
        }
    }


}

