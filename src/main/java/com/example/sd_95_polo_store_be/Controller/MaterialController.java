package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Materials;

import com.example.sd_95_polo_store_be.Service.MatarialService;

import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/material")
public class MaterialController {
    @Autowired
    private MatarialService matarialService;


    @GetMapping("")
    public Response<List<Materials>> gets() {
        return Response.ofSucceeded(matarialService.gets());
    }

    @PostMapping
    public Response<Materials> createOrUpdateMaterial(@RequestBody Materials materials) {
        try {
            Materials material = matarialService.createOrUpdate(materials);
            return Response.ofSucceeded(material);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public Response<Materials> updateMaterial(@PathVariable Integer id, @RequestBody Materials materials) {
        try {
            materials.setId(id);
            Materials material = matarialService.createOrUpdate(materials);
            return Response.ofSucceeded(material);
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping()
    public Response<List<Integer>> deleteMaterial(@RequestBody Map<String, List<Integer>> request) {
        List<Integer> ids = request.get("id");
        try {
            matarialService.deleteMaterialByIds(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }

    }
}