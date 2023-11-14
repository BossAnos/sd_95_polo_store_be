package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Dto.ProductDetailDto;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.ProductDetailRequest;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequestMapping("/ProductDetail")
@RestController
public class ProductDetailController {
    @Autowired
    ProductDetailService productDetailService;

    List<ProductDetailDto> serializeList(List<ProductDetail> lst) {
        List items = new ArrayList();
        for (ProductDetail products : lst) items.add(new ProductDetailDto(products));
        return items;
    }

    @GetMapping("/getall")
    public Response<List<ProductDetailDto>> getAll() {
        List<ProductDetail> productDetails = productDetailService.getAllProductDetail();
        List<ProductDetailDto> serializedList = serializeList(productDetails);
        return Response.ofSucceeded(serializedList);
    }

    @PostMapping("/add")
    public Response<ProductDetailDto> create(@RequestBody ProductDetailRequest request) {
        try {
            ProductDetail savedProductDetail = productDetailService.saveProductDetail(request);
            return Response.ofSucceeded(new ProductDetailDto(savedProductDetail));
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @PutMapping("/update/{id}")
    public Response<ProductDetailDto> update(@PathVariable Long id, @RequestBody ProductDetailRequest request) {
        try {
            request.setId(id);
            ProductDetail productDetail = productDetailService.saveProductDetail(request);
            return Response.ofSucceeded(new ProductDetailDto(productDetail));
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }

    @DeleteMapping("/delete")
    public Response<List<Long>> deleteMultiple(@RequestBody Map<String, List<Long>> request) {
        List<Long> ids = request.get("id");
        try {
            productDetailService.deleteProductDetailById(ids);
            return Response.ofSucceeded();
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }

    }

}
