package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    boolean existsByName(String name);
}
