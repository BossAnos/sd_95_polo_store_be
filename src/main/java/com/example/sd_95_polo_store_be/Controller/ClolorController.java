package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/color")
@RestController
public class ClolorController {
    @Autowired
    ColorService colorService;

    @GetMapping("/getall")
    public List<?> getAll() {
        return colorService.getAllColor();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Color color) {
        String error = "";
        if (ObjectUtils.isEmpty(color.getName().trim())) {
            error = "Tên không để trống";
        } else if (ObjectUtils.isEmpty(color.getStatus().toString().trim())) {
            error = "Trạng thái không để trống";
        } else if (ObjectUtils.isEmpty(color.getDescription().trim())) {
            error = "Mô tả không để trống";
        }else if (colorService.isColorDataDuplicate(color)) {
            error = "Màu này đã có rồi";
        }

        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        colorService.saveColor(color);
        return ResponseEntity.ok(color);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody Color  color) {
        String error = "";
        if (ObjectUtils.isEmpty(color.getName().trim())) {
            error = "Tên không để trống";
        } else if (ObjectUtils.isEmpty(color.getStatus().toString().trim())) {
            error = "Trạng thái không để trống";
        } else if (ObjectUtils.isEmpty(color.getDescription().trim())) {
            error = "Mô tả không để trống";
        }

        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }

        color.setId(id);
        colorService.saveColor(color);
        return ResponseEntity.ok(color);
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        colorService.deleteColorById(id);
    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Color> page = colorService.findAllColor(pageable);
        return page.toList();
    }

}
