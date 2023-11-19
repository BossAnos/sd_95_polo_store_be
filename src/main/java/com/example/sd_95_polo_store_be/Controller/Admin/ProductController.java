package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Products;
import com.example.sd_95_polo_store_be.Model.Request.ProductRequset;
import com.example.sd_95_polo_store_be.Repository.ProductRepository;
import com.example.sd_95_polo_store_be.Service.ProductService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public Response<Products> addProduct(@RequestBody ProductRequset requset){
        try {
            productService.addProduct(requset);
            return Response.ofSucceeded();
        } catch (Exception e) {
            return Response.ofError("Failed to add product: " + e.getMessage());
        }
    }
}
