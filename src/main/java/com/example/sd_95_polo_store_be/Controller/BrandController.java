package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Brands;
import com.example.sd_95_polo_store_be.Model.Request.BrandRequest;
import com.example.sd_95_polo_store_be.Service.BrandService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public Response<Brands> createBrand(@RequestBody BrandRequest request) {
        Brands createdBrand = brandService.createOrUpdate(request);
        return Response.ofSucceeded(createdBrand);
    }

    @PutMapping("/{id}")
    public Response<Brands> updateBrand(@PathVariable Integer id, @RequestBody BrandRequest request) {
        request.setId(id);
        Brands updatedBrand = brandService.createOrUpdate(request);
        return Response.ofSucceeded(updatedBrand);
    }

}
