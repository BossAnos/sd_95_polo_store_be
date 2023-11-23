package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.ImageRequest;
import com.example.sd_95_polo_store_be.Model.Response.ImageProductResponse;
import com.example.sd_95_polo_store_be.Repository.ImageRepository;

import java.util.List;

public interface ImageService {
List<ImageProductResponse> gets(Integer id);
void createOrUpdate(List<ImageRequest> images,Integer productDetailId);
}
