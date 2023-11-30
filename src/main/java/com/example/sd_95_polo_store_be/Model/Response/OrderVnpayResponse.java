package com.example.sd_95_polo_store_be.Model.Response;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class OrderVnpayResponse {
    private Integer id;
    private Float totalPrice;


}
