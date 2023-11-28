package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Entity.OderDetail;
import com.example.sd_95_polo_store_be.Model.Request.OrderDetailRequest;

public interface OrderDetailService {
    OderDetail create(OrderDetailRequest orderDetailRequest, Integer id);
}
