package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddressRequest {

    private String city;

    private String district;

    private String ward;

    private String fullAddress;
}