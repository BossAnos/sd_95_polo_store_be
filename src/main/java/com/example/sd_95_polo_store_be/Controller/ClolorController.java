package com.example.sd_95_polo_store_be.Controller;


import com.example.sd_95_polo_store_be.Model.Entity.Color;
import com.example.sd_95_polo_store_be.Service.ColorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/color")
@RestController
public class ClolorController {
    @Autowired
    ColorServices colorService;

    @GetMapping("/getall")
    public List<?> getAll() {
        return colorService.getAllColor();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Color color) {
        try {
            colorService.saveColor(color);
            return ResponseEntity.ok("Thêm màu sắc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Color color) {
        try {
            colorService.update(color, id);
            return ResponseEntity.ok("cap nhat màu sắc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            colorService.deleteColorById(id);
            return ResponseEntity.ok("Màu sắc đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Color> page = colorService.findAllColor(pageable);
        return page.toList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            colorService.deleteColorsByIds(ids);
            return ResponseEntity.ok("Các màu sắc đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
