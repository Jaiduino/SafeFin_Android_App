package com.example.insuranceapp.Model;

import com.google.gson.annotations.SerializedName;

public class Credential {

    @SerializedName("retailer_id")
    private String retailer_Id;
    private String password;

    public Credential() {
    }

    public Credential(String retailer_Id, String password) {
        this.retailer_Id = retailer_Id;
        this.password = password;
    }

    public String getRetailer_Id() {
        return retailer_Id;
    }

    public void setRetailer_Id(String retailer_Id) {
        this.retailer_Id = retailer_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "retailer_Id='" + retailer_Id + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
