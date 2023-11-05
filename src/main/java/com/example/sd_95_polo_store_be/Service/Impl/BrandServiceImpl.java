package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;
import com.example.sd_95_polo_store_be.Model.Response.BrandResponse;
import com.example.sd_95_polo_store_be.Repository.BrandRepository;
import com.example.sd_95_polo_store_be.Service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public List<Brands> gets() {
        return brandRepository.findAll();
    }

    @Override
    public Brands createOrUpdate(BrandRequest request) {
        if (request.getNameBrand() == null) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (request.getDescription() == null) {
            throw new IllegalArgumentException("Mô tả không để trống");
        }

        Optional<Brands> brands = Optional.empty();
        if (request.getId() != null) {
            brands = brandRepository.findById(request.getId());
        }

        var now = OffsetDateTime.now();

        if (brands.isPresent()) {
            Brands updateBrand = brands.get();
            updateBrand.setName(request.getNameBrand());
            updateBrand.setStatus(0);
            updateBrand.setDescription(request.getDescription());
            updateBrand.setUpdatedAt(now);
            return brandRepository.save(updateBrand);
        } else {
            Brands newBrand = new Brands();
            if (isBrand(newBrand)) {
                throw new IllegalArgumentException("Loại này đã có rồi");
            }
            newBrand.setName(request.getNameBrand());
            newBrand.setStatus(0);
            newBrand.setDescription(request.getDescription());
            newBrand.setCreatedAt(now);
            newBrand.setUpdatedAt(now);
            return brandRepository.save(newBrand);
        }
    }

    @Override
    public boolean isBrand(Brands brands) {
        Optional<Brands> existingBrand = brandRepository.findByName(brands.getName());
        return existingBrand.isPresent();
    }
}
