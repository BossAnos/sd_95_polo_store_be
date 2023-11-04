package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Categories;
import com.example.sd_95_polo_store_be.Service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/categories")
@RestController
public class CategoriesController {
    @Autowired
    CategoriesService categoriesSrevice;

    @GetMapping("/getall")
    public List<?> getAll() {
        return categoriesSrevice.getAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Categories categories) {
        try {
            categoriesSrevice.saveCategories(categories);
            return ResponseEntity.ok("Thêm loại thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Categories categories) {
        try {
            categoriesSrevice.update(categories,id);
            return ResponseEntity.ok("cập nhật loại thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            categoriesSrevice.deleteCategoriesById(id);
            return ResponseEntity.ok("Loại đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Categories> page = categoriesSrevice.findAllCategories(pageable);
        return page.toList();
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            categoriesSrevice.deleteCategoriesByIds(ids);
            return ResponseEntity.ok("Các loai đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}
