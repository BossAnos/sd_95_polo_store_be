package com.example.sd_95_polo_store_be.Controller.Admin;

import com.example.sd_95_polo_store_be.Model.Entity.Role;
import com.example.sd_95_polo_store_be.Model.Response.DiscountResponse;
import com.example.sd_95_polo_store_be.Service.RoleService;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public Response<List<Role>> gets() {
        return Response.ofSucceeded(roleService.getAll());
    }
}
