package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Repository.OrderRepository;
import com.example.sd_95_polo_store_be.Service.OrderService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Oders> getByCustomer(Integer id) {
        return orderRepository.findByCustomersId(id);
    }

    @Override
    public List<Oders> getAll() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void updateStatusOrder(Integer id, ChangeStatusOrder changeStatusOrder) {
        var now = OffsetDateTime.now();
        var order = orderRepository.findById(id).orElseThrow();
        switch (changeStatusOrder.getStatus()) {
            case 2 -> {
                order.setStatus(2);
                order.setConfirmDate(now);
                orderRepository.save(order);
            }
            case 3 -> {
                order.setStatus(3);
                order.setShipDate(now);
                orderRepository.save(order);
            }
            case 4 -> {
                order.setStatus(4);
                order.setConfirmDate(now);
                orderRepository.save(order);
            }
            case 5 -> {
                order.setStatus(5);
                orderRepository.save(order);
            }
            case 6 -> {
                if (order.getStatus() == 1
                        || order.getStatus() == 2)
                    order.setStatus(6);
                orderRepository.save(order);
            }
        }
    }


}
