package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Categories;
import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesSrevice {
    @Autowired
    CategoriesRepository categoriesRepository;
    public List<Categories> getAllCategories(){
        return categoriesRepository.findAll();
    }
    public Page<Categories> findAllCategories(Pageable pageable) {
        return categoriesRepository.findAll(pageable);
    }
    public Categories saveCategories(Categories categories){
        return categoriesRepository.save(categories);
    }

    public void deleteCategoriesById(Long id) {
        categoriesRepository.deleteById(id);
    }

    public boolean isCategoriesDataDuplicate(Categories categories) {
        return categoriesRepository.existsByName(categories.getName());
    }
    public Categories findById(Long id) {
        Optional<Categories> categories = categoriesRepository.findById(id);
        return categories.orElse(null);
    }

}
