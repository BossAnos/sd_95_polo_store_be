package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Admins;
import com.example.sd_95_polo_store_be.Model.Request.LoginRequest;
import com.example.sd_95_polo_store_be.Model.Response.AdminResponse;
import com.example.sd_95_polo_store_be.Repository.AdminRepository;
import com.example.sd_95_polo_store_be.Service.AuthSerivce;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthSerivce {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    @Transactional
    public AdminResponse adminLogin(LoginRequest request) {
        Admins admins = adminRepository.findByEmailAndPassword(request.getEmail(), request.getPassword());
        return generateAdmin(admins);
    }

    private AdminResponse generateAdmin(Admins admins) {
        return new AdminResponse().setId(admins.getId()).
                setNameAdmin(admins.getName())
                .setEmail(admins.getEmail())
                .setPhone(admins.getPhone())
                .setAvatar(admins.getAvatar())
                .setAddress(admins.getAddress())
                .setStatus(admins.getStatus())
                .setRoleId(admins.getRole().getId())
                .setNameRole(admins.getRole().getName())
                ;
    }
}
