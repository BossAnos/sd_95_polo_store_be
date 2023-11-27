package com.example.sd_95_polo_store_be.Service;
import com.example.sd_95_polo_store_be.Model.Entity.Oders;

import java.util.List;

public interface OrderService {
    List<Oders> getByCustomer(Integer id);
}
