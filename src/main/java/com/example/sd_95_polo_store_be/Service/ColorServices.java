package com.example.sd_95_polo_store_be.Service;


import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ColorServices {
    ArrayList<Colors> getAllColor();

    Colors saveColor(Colors color);

    void deleteColorById(Long id);

    Page<Colors> findAllColor(Pageable pageable);

    boolean isColorDataDuplicate(Colors color);

    Colors findById(Long id);

    void deleteColorsByIds(List<Long> ids);
}
