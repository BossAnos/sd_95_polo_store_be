package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Orders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Service.OrderService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public Response<List<Orders>> getAll() {
        return Response.ofSucceeded(orderService.getAll());
    }

    @PutMapping("/updateStatus/{id}")
    public Response<Void> updateStatus(@PathVariable Integer id,@RequestBody ChangeStatusOrder changeStatusOrder) {
        orderService.updateStatusOrder(id, changeStatusOrder);
        return Response.ofSucceeded();
    }
}
