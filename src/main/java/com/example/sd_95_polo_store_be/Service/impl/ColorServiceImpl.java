package com.example.sd_95_polo_store_be.Service.impl;

import com.example.sd_95_polo_store_be.Model.Entity.Color;
import com.example.sd_95_polo_store_be.Repository.ColorRepository;
import com.example.sd_95_polo_store_be.Service.ColorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColorServiceImpl implements ColorServices {
    @Autowired
    ColorRepository colorRepository;

    @Override
    public ArrayList<Color> getAllColor() {
        return (ArrayList<Color>) colorRepository.findAll();
    }

    @Override
    public void saveColor(Color color) {

        if (ObjectUtils.isEmpty(color.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(color.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(color.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (isColorDataDuplicate(color)) {
            throw new IllegalArgumentException("Màu này đã có rồi");
        }

        // Thực hiện lưu màu sắc vào repository
        colorRepository.save(color);
    }

    @Override
    public void update(Color color, Long id) {
        Color existingColor = findById(id);
        if (existingColor == null) {
            throw new IllegalArgumentException("Không tìm thấy màu sắc với ID: " + id);
        }
        if (ObjectUtils.isEmpty(color.getName().trim())) {
            throw new IllegalArgumentException("Tên không để trống");
        } else if (ObjectUtils.isEmpty(color.getStatus().toString().trim())) {
            throw new IllegalArgumentException("Trạng thái không để trống");
        } else if (ObjectUtils.isEmpty(color.getDescription().trim())) {
            throw new IllegalArgumentException("Mô tả không để trống");
        } else if (isColorDataDuplicate(color)) {
            throw new IllegalArgumentException("Màu này đã có rồi");
        }
        color.setId(id);
        colorRepository.save(color);

    }


    @Override
    public void deleteColorById(Long id) {

        Color existingColor = findById(id);
        if (existingColor == null) {
            throw new IllegalArgumentException("Không tìm thấy màu sắc với ID: " + id);
        }
        colorRepository.deleteById(id);
    }

    @Override
    public Page<Color> findAllColor(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }

    @Override
    public boolean isColorDataDuplicate(Color color) {
        return colorRepository.existsByName(color.getName());
    }

    @Override
    public Color findById(Long id) {
        Optional<Color> color = colorRepository.findById(id);
        return color.orElse(null);
    }

    @Override
    public void deleteColorsByIds(List<Long> ids) {
        if (ids != null && !ids.isEmpty()) {
            for (Long id : ids) {
                Optional<Color> colorOptional = colorRepository.findById(id);
                if (colorOptional.isPresent()) {
                    colorRepository.deleteById(id);
                } else {
                    throw new IllegalArgumentException("Màu sắc với ID " + id + " không tồn tại");
                }
            }
        } else {
            throw new IllegalArgumentException("Danh sách ID không hợp lệ");
        }
    }
}
