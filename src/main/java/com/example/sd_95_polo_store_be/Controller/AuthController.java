package com.example.sd_95_polo_store_be.Controller;

import com.example.sd_95_polo_store_be.Model.Request.LoginRequest;
import com.example.sd_95_polo_store_be.Model.Response.AdminResponse;
import com.example.sd_95_polo_store_be.Service.AuthSerivce;
import com.example.sd_95_polo_store_be.common.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthSerivce authSerivce;

    @PostMapping("/login")
    public Response<AdminResponse> login(@RequestBody LoginRequest loginRequest) {
        System.out.println(loginRequest);
        return Response.ofSucceeded(authSerivce.adminLogin(loginRequest));
    }
}