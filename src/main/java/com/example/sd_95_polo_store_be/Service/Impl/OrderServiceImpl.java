package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Oders;
import com.example.sd_95_polo_store_be.Model.Request.ChangeStatusOrder;
import com.example.sd_95_polo_store_be.Model.Request.OrderDetailRequest;
import com.example.sd_95_polo_store_be.Model.Request.OrderRequest;
import com.example.sd_95_polo_store_be.Model.Response.CartDetailResponse;
import com.example.sd_95_polo_store_be.Model.Response.CartResponse;
import com.example.sd_95_polo_store_be.Repository.CartDetailRepository;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Repository.OrderRepository;
import com.example.sd_95_polo_store_be.Service.CartService;
import com.example.sd_95_polo_store_be.Service.OrderDetailService;
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
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartDetailRepository cartDetailRepository;

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
            case 1 -> {
                order.setStatus(1);
                orderRepository.save(order);
            }
            case 2 -> {
                order.setStatus(2);
                order.setConfirmDate(now);
                orderRepository.save(order);
            }
            case 3 -> {
                order.setStatus(3);
                orderRepository.save(order);
            }
            case 4 -> {
                order.setStatus(4);
                order.setShipDate(now);
                orderRepository.save(order);
            }
            case 5 -> {
                order.setStatus(5);
                order.setSuccessDate(now);
                orderRepository.save(order);
            }
            case 6 -> {
                order.setStatus(6);
                order.setSuccessDate(now);
                orderRepository.save(order);
            }
            case 7 -> {
                if (order.getStatus() == 1 || order.getStatus() == 3
                        || order.getStatus() == 2 || order.getStatus() == 6)
                    order.setStatus(7);
                orderRepository.save(order);
            }
            case 8 -> {
                order.setStatus(8);
                orderRepository.save(order);
            }
        }
    }

    @Override
    public void orderOnline(OrderRequest orderRequest, Integer id) {
        var now = OffsetDateTime.now();
        var customer = customerRepository.findById(id).orElseThrow();
        Oders oders = new Oders();
        oders.setAddress(orderRequest.getAddress());
        oders.setTotalPrice(orderRequest.getTotalPrice());
        oders.setStatus(1);
        oders.setCustomers(customer);
        oders.setUsername(orderRequest.getUsername());
        oders.setPhone(orderRequest.getPhone());
        oders.setCreatedAt(now);
        oders.setUpdatedAt(now);
        orderRepository.save(oders);

        List<OrderDetailRequest> orderDetailRequests = orderRequest.getOrderDetailRequest();
        orderDetailRequests.forEach(request -> orderDetailService.create(request, oders.getId()));

        CartResponse cartResponse = cartService.getOneByStatus(id);
        List<CartDetailResponse> list = cartResponse.getCartDetailResponses();
        list.forEach(cartDetail -> cartDetailRepository.deleteById(cartDetail.getCartDetailId()));


    }


}
