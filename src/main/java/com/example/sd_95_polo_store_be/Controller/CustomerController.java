package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Service.CustomerService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
//
//    @GetMapping("/{id}")
//    public Response<CustomerResponse> getOne(@PathVariable Integer id){
//        return Response.ofSucceeded(customerService.getOne(id));
//    }
}
