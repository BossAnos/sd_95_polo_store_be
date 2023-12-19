package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Entity.Role;
import com.example.sd_95_polo_store_be.Repository.RoleRepository;
import com.example.sd_95_polo_store_be.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;


    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
