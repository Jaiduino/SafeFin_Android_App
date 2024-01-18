package com.example.insuranceapp.Dtos;

import android.util.Log;

public class Customer_Dto {
    private Long customer_Id;
    private String full_Name;
    private String aadhar_No;
    private String contact_No;
    private String email_Address;
    private String date_of_birth;
    private String full_address;
    private String city;
    private String pincode;
    private String state;
    private String country;

    public Customer_Dto() {
    }

    public Customer_Dto(Long customer_Id, String full_Name, String aadhar_No, String contact_No, String email_Address, String date_of_birth, String full_address, String city, String pincode, String state, String country) {
        this.customer_Id = customer_Id;
        this.full_Name = full_Name;
        this.aadhar_No = aadhar_No;
        this.contact_No = contact_No;
        this.email_Address = email_Address;
        this.date_of_birth = date_of_birth;
        this.full_address = full_address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    public Long getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(Long customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getFull_Name() {
        return full_Name;
    }

    public void setFull_Name(String full_Name) {
        this.full_Name = full_Name;
    }

    public String getAadhar_No() {
        return aadhar_No;
    }

    public void setAadhar_No(String aadhar_No) {
        this.aadhar_No = aadhar_No;
    }

    public String getContact_No() {
        return contact_No;
    }

    public void setContact_No(String contact_No) {
        this.contact_No = contact_No;
    }

    public String getEmail_Address() {
        return email_Address;
    }

    public void setEmail_Address(String email_Address) {
        this.email_Address = email_Address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
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

    @Override
    public String toString() {
        return "Customer_Dto{" +
                "customer_Id=" + customer_Id +
                ", full_Name='" + full_Name + '\'' +
                ", aadhar_No='" + aadhar_No + '\'' +
                ", contact_No='" + contact_No + '\'' +
                ", email_Address='" + email_Address + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", full_address='" + full_address + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
