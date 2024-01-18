package com.example.insuranceapp.Model;


import com.google.gson.annotations.SerializedName;

public class Device {
    @SerializedName("device_details_Id")
    private int device_details_Id;
    @SerializedName("imei_no")
    private String imei_no;
    @SerializedName("device_brand_name")
    private String device_brand_name;
    @SerializedName("device_model_name")
    private String device_model_name;
    @SerializedName("device_price")
    private double device_price;
    @SerializedName("category")
    private Category category;

    public Device() {
    }

    public Device(int device_details_Id, String imei_no, String device_brand_name, String device_model_name, double device_price, Category category) {
        this.device_details_Id = device_details_Id;
        this.imei_no = imei_no;
        this.device_brand_name = device_brand_name;
        this.device_model_name = device_model_name;
        this.device_price = device_price;
        this.category = category;
    }
    public Device( String imei_no, String device_brand_name, String device_model_name, double device_price, Category category) {
        this.device_details_Id = 0;
        this.imei_no = imei_no;
        this.device_brand_name = device_brand_name;
        this.device_model_name = device_model_name;
        this.device_price = device_price;
        this.category = category;
    }

    public int getDevice_details_Id() {
        return device_details_Id;
    }

    public void setDevice_details_Id(int device_details_Id) {
        this.device_details_Id = device_details_Id;
    }

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public String getDevice_brand_name() {
        return device_brand_name;
    }

    public void setDevice_brand_name(String device_brand_name) {
        this.device_brand_name = device_brand_name;
    }

    public String getDevice_model_name() {
        return device_model_name;
    }

    public void setDevice_model_name(String device_model_name) {
        this.device_model_name = device_model_name;
    }

    public double getDevice_price() {
        return device_price;
    }

    public void setDevice_price(double device_price) {
        this.device_price = device_price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Device{" +
                "device_details_Id=" + device_details_Id +
                ", imei_no='" + imei_no + '\'' +
                ", device_brand_name='" + device_brand_name + '\'' +
                ", device_model_name='" + device_model_name + '\'' +
                ", device_price=" + device_price +
                ", category=" + category +
                '}';
    }
}
