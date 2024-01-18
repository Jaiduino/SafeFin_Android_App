package com.example.insuranceapp.Backend_Services;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Category_Services {
    @GET("category/get_All_Categories")
    public Call<JsonObject> Get_All_Categories();
}
