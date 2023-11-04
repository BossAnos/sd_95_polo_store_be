package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories,Long> {
    boolean existsByName(String name);
    Optional<Categories> findById(Long id);
}
