package com.example.sd_95_polo_store_be.Repository;

import com.example.sd_95_polo_store_be.Model.Entity.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {
    boolean existsByName(String name);
//    void deleteAllById(List<Long> ids);
Optional<Color> findById(Long id);
}
