package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Service.DiscountService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/discount")
public class DiscountController {
    @Autowired
    private DiscountService discountService;

    @GetMapping
    public Response<List<DiscountResponse>> gets(){
        return Response.ofSucceeded(discountService.gets());
    }
}
