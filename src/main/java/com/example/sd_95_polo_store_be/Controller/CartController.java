package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeQuantityCartRequest;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusCartResponse;
import com.example.sd_95_polo_store_be.Model.Response.CartResponse;
import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;
import com.example.sd_95_polo_store_be.Service.CartDetailServie;
import com.example.sd_95_polo_store_be.Service.CartService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartDetailServie cartDetailServie;
    @Autowired
    private CartService cartService;

    @PostMapping("/{id}")
    public Response<Void> add(@RequestBody CartRequest cartRequest, @PathVariable Integer id) {
        cartDetailServie.addCart(cartRequest, id);
        return Response.ofSucceeded();
    }

    @GetMapping("/{id}")
    public Response<CartResponse> getOne(@PathVariable Integer id) {
        return Response.ofSucceeded(cartService.getOne(id));
    }

    @PutMapping("updateQuantity/{id}")
    public Response<Void> updateQuantity(@PathVariable Long id, @RequestBody ChangeQuantityCartRequest quantityCartRequest) {
        cartDetailServie.changeQuantityCart(id, quantityCartRequest);
        return Response.ofSucceeded();
    }

    @PutMapping("updateStatus/{id}")
    public Response<Void> updateStatus(@PathVariable Long id, @RequestBody ChangeStatusCartResponse statusCartResponse) {
        cartDetailServie.changeStatusCart(id, statusCartResponse);
        return Response.ofSucceeded();
    }

    @GetMapping("order/{id}")
    public Response<CartResponse> getOneByTrangThai(@PathVariable Integer id){
        return Response.ofSucceeded(cartService.getOneByStatus(id));
    }


}
