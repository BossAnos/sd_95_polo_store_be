package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;
import com.example.sd_95_polo_store_be.Service.BrandService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("")
    public Response<List<Brands>> gets() {
        return Response.ofSucceeded(brandService.gets());
    }

    @PostMapping
    public Response<Brands> createOrUpdateBrand(@RequestBody BrandRequest request) {
        try {
           Brands brands = brandService.createOrUpdate(request);
            return Response.ofSucceeded(brands);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Response<Brands> updateBrand(@PathVariable Integer id, @RequestBody BrandRequest request) {
        try {
            request.setId(id);
            Brands createdBrand = brandService.createOrUpdate(request);
            return Response.ofSucceeded(createdBrand);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping()
    public Response<List<Integer>> deleteBrand(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> ids = request.get("id");
        try {
            brandService.deleteBrandByIds(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }

    }
}
