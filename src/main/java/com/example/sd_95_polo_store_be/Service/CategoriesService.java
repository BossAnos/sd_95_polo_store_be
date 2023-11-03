package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Categories;
import com.example.sd_95_polo_store_be.Model.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface CategoriesService {
    ArrayList<Categories> getAllCategories();
    void saveCategories(Categories categories);
    void update(Categories categories,Long id);
    void deleteCategoriesById(Long id);
    Page<Categories> findAllCategories(Pageable pageable);
    boolean isCategoriesDataDuplicate(Categories categories);
    Categories findById(Long id);
    void deleteCategoriesByIds(List<Long> ids);
}
