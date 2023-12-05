package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.OrderDetail;
import com.example.sd_95_polo_store_be.Model.Request.OrderDetailRequest;
import com.example.sd_95_polo_store_be.Repository.OrderDetailRepository;
import com.example.sd_95_polo_store_be.Repository.OrderRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public OrderDetail create(OrderDetailRequest orderDetailRequest, Integer id) {
        var now = OffsetDateTime.now();
        var order = orderRepository.findById(id) .orElseThrow(() -> new IllegalArgumentException("Invoice not found with ID: " + id));
        var productDetail = productDetailRepository.findById(orderDetailRequest.getProductDetaiId()).orElseThrow();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductDetail(productDetail);
        orderDetail.setPrice(orderDetailRequest.getPrice());
        orderDetail.setQuantity(orderDetailRequest.getQuantity());
        orderDetail.setCreateDate(now);
        orderDetail.setUpdatedAt(now);
        orderDetail.setStatus(1);
        orderDetail.setOrders(order);
      return   orderDetailRepository.save(orderDetail);
    }
}
