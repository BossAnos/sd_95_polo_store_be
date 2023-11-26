package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Cart;
import com.example.sd_95_polo_store_be.Model.Entity.CartDetail;
import com.example.sd_95_polo_store_be.Model.Entity.Customers;
import com.example.sd_95_polo_store_be.Model.Entity.ProductDetail;
import com.example.sd_95_polo_store_be.Model.Request.CartRequest;
import com.example.sd_95_polo_store_be.Repository.CartDetailRepository;
import com.example.sd_95_polo_store_be.Repository.CartRepository;
import com.example.sd_95_polo_store_be.Repository.CustomerRepository;
import com.example.sd_95_polo_store_be.Repository.ProductDetailRepository;
import com.example.sd_95_polo_store_be.Service.CartDetailServie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartDetailServiceImpl implements CartDetailServie {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void addCart(CartRequest cartRequest, Integer id) {
        Optional<Cart> optionalCart = cartRepository.findCartByCustomersId(id);
        var customer = customerRepository.findById(id).orElseThrow();
        Cart cart;
        if (optionalCart.isPresent()) {
            cart = optionalCart.get();
        } else {
            cart = new Cart();
            cart.setStatus(1);
            cart.setCustomers(customer);
            cart = cartRepository.save(cart);
        }

        Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(cartRequest.getProductDetailId());
        if (optionalProductDetail.isPresent()) {
            ProductDetail productDetail = optionalProductDetail.get();
            Optional<CartDetail> optionalCartDetail = cartDetailRepository.findCartDetailByCartAndAndProductDetail(cart, productDetail);
            if (optionalCartDetail.isPresent()) {
                CartDetail cartDetail = optionalCartDetail.get();
                Integer currentQuantity = cartDetail.getQuantity();
                Integer newQuantity = currentQuantity + cartRequest.getQuantity();
                cartDetail.setQuantity(newQuantity);
                cartDetailRepository.save(cartDetail);
            } else {
                CartDetail newCartDetail = new CartDetail();
                newCartDetail.setQuantity(cartRequest.getQuantity());
                newCartDetail.setStatus(1);
                newCartDetail.setCart(cart);
                newCartDetail.setProductDetail(productDetail);
                cartDetailRepository.save(newCartDetail);
            }
        }
    }
}
