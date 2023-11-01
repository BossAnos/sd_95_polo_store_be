package com.example.sd_95_polo_store_be.Service;

import com.example.sd_95_polo_store_be.Model.Request.LoginRequest;
import com.example.sd_95_polo_store_be.Model.Response.AdminResponse;

public interface AuthSerivce {
    AdminResponse adminLogin(LoginRequest request);
}
