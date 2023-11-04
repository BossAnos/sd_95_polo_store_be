package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface ColorServices {
    ArrayList<Color> getAllColor(


    );

    void saveColor(Color color);

    void update(Color color, Long id);

    void deleteColorById(Long id);

    Page<Color> findAllColor(Pageable pageable);

    boolean isColorDataDuplicate(Color color);

    Color findById(Long id);

    void deleteColorsByIds(List<Long> ids);
}
