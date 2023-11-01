package com.example.sd_95_polo_store_be.Service;


import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Repository.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ColorService {
    @Autowired
    ColorRepository colorRepository;
    public List<Color> getAllColor(){
        return colorRepository.findAll();
    }
    public Page<Color> findAllColor(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }
    public Color saveColor(Color color){
        return colorRepository.save(color);
    }

    public void deleteColorById(Long id) {
        colorRepository.deleteById(id);
    }

    public boolean isColorDataDuplicate(Color color) {
        return colorRepository.existsByName(color.getName());
    }
    public Color findById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.orElse(null);
    }




}
