package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Model.Request.OrderRequest;
import com.example.sd_95_polo_store_be.Service.OrderService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer/order")
public class UserOrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public Response<List<Oders>> getOne(@PathVariable Integer id) {
        return Response.ofSucceeded(orderService.getByCustomer(id));
    }

    @PostMapping("/{id}")
    public Response<Oders> createOrderOnline(@RequestBody OrderRequest orderRequest, @PathVariable Integer id) {
        orderService.orderOnline(orderRequest, id);
        return Response.ofSucceeded();
    }
}
