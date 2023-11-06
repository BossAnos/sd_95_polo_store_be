package com.example.sd_95_polo_store_be.Controller;



import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Service.ColorServices;
import com.example.sd_95_polo_store_be.common.Response;
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
    private ColorServices colorServices;

    @GetMapping("/getall")
    public Response<List<Colors>> getAll() {
        return Response.ofSucceeded(colorServices.getAllColor());
    }

    @PostMapping("/add")
    public ResponseEntity<?> create(@RequestBody Colors color) {
        try {
            colorServices.saveColor(color);
            return ResponseEntity.ok("Thêm màu sắc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Colors color) {
        try {
            colorServices.update(color, id);
            return ResponseEntity.ok("cap nhat màu sắc thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        try {
            colorServices.deleteColorById(id);
            return ResponseEntity.ok("Màu sắc đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/get-page")
    public List<?> getPage(@RequestParam(defaultValue = "0") int p) {
        Pageable pageable = PageRequest.of(p, 5);
        Page<Colors> page = colorServices.findAllColor(pageable);
        return page.toList();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            colorServices.deleteColorsByIds(ids);
            return ResponseEntity.ok("Các màu sắc đã được xóa thành công");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
