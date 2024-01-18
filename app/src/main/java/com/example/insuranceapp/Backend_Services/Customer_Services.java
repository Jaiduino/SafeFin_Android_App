package com.example.insuranceapp.Backend_Services;

import com.example.insuranceapp.Model.Credential;
import com.example.insuranceapp.Model.Customer;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Customer_Services {
    @POST("customer/save_customer_info")
    public Call<JsonObject> Save_Customer(@Body Customer customer);

    @GET("customer/get_Customer_By_Id_for_Retailer/{customer_Id}")
    public Call<JsonObject> Get_Customer(@Path("customer_Id") Long id);

}
