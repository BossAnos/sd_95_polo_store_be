package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> getForCustomer(Integer id);
}
