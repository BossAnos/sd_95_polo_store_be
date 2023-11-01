package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Categories;
import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Service.CategoriesSrevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class CategoriesController {
    @Autowired
    CategoriesSrevice categoriesSrevice;

    @GetMapping("/getall")
    public List<?> getAll() {
        return categoriesSrevice.getAllCategories();
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Categories categories) {
        String error = "";
        if (ObjectUtils.isEmpty(categories.getName().trim())) {
            error = "Tên không để trống";
        } else if (ObjectUtils.isEmpty(categories.getStatus().toString().trim())) {
            error = "Trạng thái không để trống";
        } else if (ObjectUtils.isEmpty(categories.getDescription().trim())) {
            error = "Mô tả không để trống";
        }else if (categoriesSrevice.isCategoriesDataDuplicate(categories)) {
            error = "Loại này đã có rồi";
        }

        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);

        }
        categoriesSrevice.saveCategories(categories);
        return ResponseEntity.ok("Thêm loại thành công");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable  Long id, @RequestBody Categories categories) {
       Categories existingCategories = categoriesSrevice.findById(id);
        if (existingCategories == null) {
            String error = "Không tìm thấy loại với ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        String error = "";
        if (ObjectUtils.isEmpty(categories.getName().trim())) {
            error = "Tên không để trống";
        } else if (ObjectUtils.isEmpty(categories.getStatus().toString().trim())) {
            error = "Trạng thái không để trống";
        } else if (ObjectUtils.isEmpty(categories.getDescription().trim())) {
            error = "Mô tả không để trống";
        }

        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }

        categories.setId(id);
        categoriesSrevice.saveCategories(categories);
        return ResponseEntity.ok(categories);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Categories existingCategories = categoriesSrevice.findById(id);
        if (existingCategories == null) {
            String error = "Không tìm thấy loại  với ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        categoriesSrevice.deleteCategoriesById(id);
        return ResponseEntity.ok("Loại đã được xóa thành công");
    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Categories> page = categoriesSrevice.findAllCategories(pageable);
        return page.toList();
    }


}
