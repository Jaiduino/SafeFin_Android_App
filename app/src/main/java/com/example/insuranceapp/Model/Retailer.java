package com.example.insuranceapp.Model;

import com.google.gson.JsonArray;

import org.json.JSONArray;

import java.util.List;

public class Retailer {
    private long retailer_Id;
    private String owner_name;
    private String shop_name;
    private String email_address;
    private String owner_phone_no;
    private String shop_gst_no;
    private String password;
    private Retailer_Address address;

    private JsonArray application_list;

    public Retailer() {
    }

    public Retailer(long retailer_Id, String owner_name, String shop_name, String email_address, String owner_phone_no, String shop_gst_no, String password, Retailer_Address address, JsonArray application_list) {
        this.retailer_Id = retailer_Id;
        this.owner_name = owner_name;
        this.shop_name = shop_name;
        this.email_address = email_address;
        this.owner_phone_no = owner_phone_no;
        this.shop_gst_no = shop_gst_no;
        this.password = password;
        this.address = address;
        this.application_list = application_list;
    }

    public long getRetailer_Id() {
        return retailer_Id;
    }

    public void setRetailer_Id(long retailer_Id) {
        this.retailer_Id = retailer_Id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getOwner_phone_no() {
        return owner_phone_no;
    }

    public void setOwner_phone_no(String owner_phone_no) {
        this.owner_phone_no = owner_phone_no;
    }

    public String getShop_gst_no() {
        return shop_gst_no;
    }

    public void setShop_gst_no(String shop_gst_no) {
        this.shop_gst_no = shop_gst_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Retailer_Address getAddress() {
        return address;
    }

    public void setAddress(Retailer_Address address) {
        this.address = address;
    }

    public JsonArray getApplication_list() {
        return application_list;
    }

    public void setApplication_list(JsonArray application_list) {
        this.application_list = application_list;
    }

    @Override
    public String toString() {
        return "Retailer{" +
                "retailer_Id=" + retailer_Id +
                ", owner_name='" + owner_name + '\'' +
                ", shop_name='" + shop_name + '\'' +
                ", email_address='" + email_address + '\'' +
                ", owner_phone_no='" + owner_phone_no + '\'' +
                ", shop_gst_no='" + shop_gst_no + '\'' +
                ", password='" + password + '\'' +
                ", address=" + address +
                ", application_list=" + application_list +
                '}';
    }
}
