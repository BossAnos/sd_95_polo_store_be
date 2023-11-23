package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Images;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusImage;
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
    public void updateStatus(Integer id, ChangeStatusImage changeStatusImage) {
        var image = imageRepository.findById(id).orElseThrow();

        if (changeStatusImage.getStatus() != 0) {
            image.setStatus(0);
            imageRepository.save(image);
        } else {
            image.setStatus(1);
            imageRepository.save(image);
        }
    }

    @Override
    public void deleteOne(Integer id) {
        var image = imageRepository.findById(id).orElseThrow();
        imageRepository.deleteImage(id);
    }

    @Override
    public void createOrUpdate(List<ImageRequest> images, Integer productDetailId) {
        var now = OffsetDateTime.now();
        var productDetail = productDetailRepository.findById(productDetailId)
                .orElseThrow(() -> new IllegalArgumentException("Product detail not found."));

        for (int i = 0; i < images.size(); i++) {
            ImageRequest imageRequest = images.get(i);

            if (imageRequest.getId() == null) {
                // Check if an image with the same name already exists for the given product detail
                Images existingImage = imageRepository.findByNameAndProductDetail(imageRequest.getName(), productDetail);

                if (existingImage == null) {
                    Images newImage = new Images();
                    newImage.setName(imageRequest.getName());
                    newImage.setUrl_image(String.valueOf(i));
                    newImage.setStatus(1);
                    newImage.setCreatedAt(now);
                    newImage.setUpdatedAt(now);
                    newImage.setProductDetail(productDetail);
                    imageRepository.save(newImage);
                }
            } else {
                var imageUpdate = imageRepository.findById(imageRequest.getId())
                        .orElseThrow(() -> new IllegalArgumentException("Image not found."));

                if (!imageUpdate.getName().equals(imageRequest.getName())) {
                    // Only update the image if the name is different
                    imageUpdate.setName(imageRequest.getName());
                    imageUpdate.setUpdatedAt(now);
                    imageRepository.save(imageUpdate);
                }
            }
        }
    }


    @Override
    public void delete(List<ImageRequest> imageDelete, Integer id) {
        imageDelete.forEach(imageRequest -> imageRepository.deleteImage(imageRequest.getId()));
    }


}

