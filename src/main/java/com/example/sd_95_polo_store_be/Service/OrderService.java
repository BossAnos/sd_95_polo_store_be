package com.example.sd_95_polo_store_be.Service;
import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Model.Request.OrderRequest;
import com.example.sd_95_polo_store_be.Model.Response.OrderResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderVnpayResponse;

import java.util.List;

public interface OrderService {
    List<Oders> getByCustomer(Integer id);
    List<Oders> getAll();
    void updateStatusOrder(Integer id, ChangeStatusOrder changeStatusOrder);
    OrderVnpayResponse orderOnline(OrderRequest orderRequest, Integer id);
    Oders get(Integer id);
    void changeTransaction(Integer id);

    OrderResponse getOneOrder(Integer id);
}
