package com.example.insuranceapp.Backend_Services;

import com.example.insuranceapp.Model.Credential;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Retailer_Services {
    @POST("retailer/login_retailer")
    public Call<JsonObject> Login_Retailer(@Body Credential cre);

    @GET("retailer/get_Retailer_By_id/{id}")
    public Call<JsonObject> Get_retailer_Details_By_Id(@Path("id") Long id);
}
