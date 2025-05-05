package com.example.fitgo.api;

import com.example.fitgo.model.LoginResponse;
import com.example.fitgo.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

    @POST("/api/auth/login")
    Call<LoginResponse> login(@Body User user);
    @POST("/api/auth/register")
    Call<String> registerUser(@Body User user);
}
