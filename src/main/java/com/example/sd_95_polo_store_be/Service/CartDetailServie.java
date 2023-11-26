package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.CartRequest;

public interface CartDetailServie {
    public void addCart(CartRequest cartRequest,Integer id);
}
