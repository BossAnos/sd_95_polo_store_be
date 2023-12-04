package com.example.sd_95_polo_store_be.Model.Request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class OrderRequest {
    private String phone;
    private String username;
    private String address;
    private Float totalPrice;

    List<OrderDetailRequest> orderDetailRequest;
}
