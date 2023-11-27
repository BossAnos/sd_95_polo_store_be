package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Service.CartDetailServie;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartDetailServie cartDetailServie;
    @PostMapping("/{id}")
    public Response<Void> getOne(@RequestBody CartRequest cartRequest, @PathVariable Integer id){
        cartDetailServie.addCart(cartRequest,id);
        return Response.ofSucceeded();
    }
}
