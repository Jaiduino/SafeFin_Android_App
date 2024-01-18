package com.example.insuranceapp.Model;

public class Retailer_Address {
    private int retailer_address_Id;
    private String full_address;
    private String city;
    private String pincode;
    private String state;
    private String country;

    public Retailer_Address() {
    }

    public Retailer_Address(int retailer_address_Id, String full_address, String city, String pincode, String state, String country) {
        this.retailer_address_Id = retailer_address_Id;
        this.full_address = full_address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    public int getRetailer_address_Id() {
        return retailer_address_Id;
    }

    public void setRetailer_address_Id(int retailer_address_Id) {
        this.retailer_address_Id = retailer_address_Id;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
