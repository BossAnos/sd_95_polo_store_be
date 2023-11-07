package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Dto.ProductDetailDto;
import com.example.sd_95_polo_store_be.Model.Entity.Colors;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public Response<ProductDetailDto> create(@RequestBody ProductDetail productDetail) {
        try {
            ProductDetail savedProductDetail = productDetailService.saveProductDetail(productDetail);
            return Response.ofSucceeded(new ProductDetailDto(savedProductDetail));
        } catch (IllegalArgumentException e) {
            return Response.ofError(e.getMessage());
        }
    }
}
