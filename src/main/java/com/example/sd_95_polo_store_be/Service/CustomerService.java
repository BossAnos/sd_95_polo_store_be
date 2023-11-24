package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Response.CustomerResponse;

public interface CustomerService {
    CustomerResponse getOne(Integer id);
}
