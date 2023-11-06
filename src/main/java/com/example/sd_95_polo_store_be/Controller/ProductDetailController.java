package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Dto.ProductDetailDto;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<?> getAll() {
        return (serializeList(productDetailService.getAll()));
    }
}
