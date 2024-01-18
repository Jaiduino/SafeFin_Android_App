package com.example.insuranceapp.Model;

public class Customer_Address {

    private int customer_address_Id;
    private String full_address;
    private String city;
    private String pincode;
    private String state;
    private String country;

    public Customer_Address() {
    }

    public Customer_Address( String full_address, String city, String pincode, String state, String country) {
        this.customer_address_Id = 00;
        this.full_address = full_address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    public Customer_Address(int customer_address_Id, String full_address, String city, String pincode, String state, String country) {
        this.customer_address_Id = customer_address_Id;
        this.full_address = full_address;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    public int getCustomer_address_Id() {
        return customer_address_Id;
    }

    public void setCustomer_address_Id(int customer_address_Id) {
        this.customer_address_Id = customer_address_Id;
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
        return "Customer_Address{" +
                "customer_address_Id=" + customer_address_Id +
                ", full_address='" + full_address + '\'' +
                ", city='" + city + '\'' +
                ", pincode='" + pincode + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
