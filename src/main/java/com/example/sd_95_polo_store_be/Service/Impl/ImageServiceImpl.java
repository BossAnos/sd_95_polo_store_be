package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Request.ImageRequest;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import com.example.sd_95_polo_store_be.Repository.ImageRepository;
import com.example.sd_95_polo_store_be.Service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<ImageProductResponse> gets(Long id) {
        return imageRepository.findByProductDetail(id);
    }

    @Override
    public void createOrUpdate(List<ImageRequest> images, Long productDetailId) {

    }


}

