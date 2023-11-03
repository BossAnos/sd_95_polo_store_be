package com.example.sd_95_polo_store_be.Service.impl;

import com.example.sd_95_polo_store_be.Model.Categories;
import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Repository.CategoriesRepository;
import com.example.sd_95_polo_store_be.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesServiceImpl implements CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    @Override
    public ArrayList<Categories> getAllCategories() {
        return (ArrayList<Categories>) categoriesRepository.findAll();
    }

    @Override
    public void saveCategories(Categories categories) {
        if (ObjectUtils.isEmpty(categories.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(categories.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(categories.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (isCategoriesDataDuplicate(categories)) {
            throw new IllegalArgumentException("Loại này đã có rồi");
        }
        categoriesRepository.save(categories);
    }

    @Override
    public void update(Categories categories, Long id) {
        Categories existingCategories = findById(id);
        if (existingCategories == null) {
            throw new IllegalArgumentException("Không tìm thấy loại với ID: " + id);
        }
        if (ObjectUtils.isEmpty(categories.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(categories.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(categories.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (isCategoriesDataDuplicate(categories)) {
            throw new IllegalArgumentException("Loại này đã có rồi");
        }
        categories.setId(id);
        categoriesRepository.save(categories);

    }

    @Override
    public void deleteCategoriesById(Long id) {
        Categories existingCategories = findById(id);
        if (existingCategories == null) {
            throw new IllegalArgumentException("Không tìm thấy loại với ID: " + id);
        }
        categoriesRepository.deleteById(id);
    }

    @Override
    public Page<Categories> findAllCategories(Pageable pageable) {
        return categoriesRepository.findAll(pageable);
    }

    @Override
    public boolean isCategoriesDataDuplicate(Categories categories) {
        return categoriesRepository.existsByName(categories.getName());
    }

    @Override
    public Categories findById(Long id) {
        Optional<Categories> categories = categoriesRepository.findById(id);
        return categories.orElse(null);
    }

    @Override
    public void deleteCategoriesByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                Optional<Categories> categoriesOptional = categoriesRepository.findById(id);
                if (categoriesOptional.isPresent()) {
                    categoriesRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Loại với ID " + id + " không tồn tại");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }

    }
}
