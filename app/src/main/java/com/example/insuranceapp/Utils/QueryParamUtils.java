package com.example.insuranceapp.Utils;

import com.example.insuranceapp.Dtos.Save_Application_Dto;
import com.example.insuranceapp.Model.Category;
import com.example.insuranceapp.Model.Device;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class QueryParamUtils {
    public static String convertToQueryParam(Save_Application_Dto app) {
        StringBuilder queryParams = new StringBuilder();

        try {
            // Convert outer key
            queryParams.append("payment_mode=").append(URLEncoder.encode(app.getPayment_mode(), StandardCharsets.UTF_8.toString()));
            queryParams.append("invoice_No_or_loan_Id_No=").append(URLEncoder.encode(app.getInvoice_No_or_loan_Id_No(), StandardCharsets.UTF_8.toString()));

            // Convert inner object
            if (app.getDevice() != null) {
               Device d = app.getDevice();
                queryParams.append("device.device_details_Id").append(URLEncoder.encode(String.valueOf( d.getDevice_details_Id()), StandardCharsets.UTF_8.toString()));
                queryParams.append("device.imei_no=").append(URLEncoder.encode(d.getImei_no(), StandardCharsets.UTF_8.toString()));
                queryParams.append("device.device_brand_name=").append(URLEncoder.encode(d.getDevice_brand_name(), StandardCharsets.UTF_8.toString()));
                queryParams.append("device_model_name=").append(URLEncoder.encode(d.getDevice_model_name(), StandardCharsets.UTF_8.toString()));
                queryParams.append("device_price=").append(URLEncoder.encode(String.valueOf(d.getDevice_price()), StandardCharsets.UTF_8.toString()));
               if(d.getCategory()!=null){
                   Category cat = d.getCategory();
                   queryParams.append("device.category.id=").append(URLEncoder.encode(String.valueOf( cat.getId()), StandardCharsets.UTF_8.toString()));
                   queryParams.append("device.category.category_name=").append(URLEncoder.encode(cat.getCategory_name(), StandardCharsets.UTF_8.toString()));
               }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }

        return queryParams.toString();
    }
}
