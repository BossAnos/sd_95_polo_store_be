package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Service.OrderService;
import com.example.sd_95_polo_store_be.common.Response;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Response<List<Oders>> getAll(){
        return Response.ofSucceeded(orderService.getAll());
    }
}
