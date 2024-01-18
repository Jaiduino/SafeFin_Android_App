package com.example.insuranceapp.Backend_Services;

import com.example.insuranceapp.Model.Customer;
import com.google.gson.JsonObject;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Application_Services {
    @Multipart
    @POST("loan_application/save_loan_application_info")
    public Call<JsonObject> Save_Application(@Part("customer_id") Long c_id, @Part("retailer_Id") Long r_id, @Part("application") RequestBody app, @Part MultipartBody.Part image1,
                                             @Part MultipartBody.Part image2);
}
