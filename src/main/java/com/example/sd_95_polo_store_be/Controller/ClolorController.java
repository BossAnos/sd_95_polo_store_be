package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Color;
import com.example.sd_95_polo_store_be.Service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        } else if (colorService.isColorDataDuplicate(color)) {
            error = "Màu này đã có rồi";
        }

        if (!error.isEmpty()) {
            return ResponseEntity.badRequest().body(error);
        }
        colorService.saveColor(color);
        return ResponseEntity.ok("Thêm màu sắc thành công");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Color color) {
        Color existingColor = colorService.findById(id);
        if (existingColor == null) {
            String error = "Không tìm thấy màu sắc với ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
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
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Color existingColor = colorService.findById(id);
        if (existingColor == null) {
            String error = "Không tìm thấy màu sắc với ID: " + id;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        colorService.deleteColorById(id);
        return ResponseEntity.ok("Màu sắc đã được xóa thành công");
    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Color> page = colorService.findAllColor(pageable);
        return page.toList();
    }

    @DeleteMapping("/deleteAll/{ids}")
    public ResponseEntity<String> deleteMultiple(@PathVariable List<Long> ids) {
        List<String> errors = new ArrayList<>();
        List<String> successMessages = new ArrayList<>();

        for (Long id : ids) {
            Color existingColor = colorService.findById(id);
            if (existingColor == null) {
                errors.add("Không tìm thấy màu sắc với ID: " + id);
            } else {
                colorService.deleteColorById(id);
                successMessages.add("Màu sắc với ID " + id + " đã được xóa thành công");
            }
        }

        if (!errors.isEmpty()) {
            String error = String.join(", ", errors);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        String successMessage = String.join(", ", successMessages);
        return ResponseEntity.ok(successMessage);
    }
}
