package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.AddressResponse;
import com.example.sd_95_polo_store_be.Repository.AddressRepository;
import com.example.sd_95_polo_store_be.Service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<AddressResponse> getForCustomer(Integer id) {
        List<AddressResponse> addressResponses = addressRepository.getByProductId(id);
        return addressResponses;
    }
}
