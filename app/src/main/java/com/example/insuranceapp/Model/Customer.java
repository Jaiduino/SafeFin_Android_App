package com.example.insuranceapp.Model;

public class Customer {
    private long customer_Id;
    private String password;
    private String fullName;
    private String aadhar_no;
    private String contact_no;
    private String email_address;
    private String date_of_birth;
    private Customer_Address address;

    public Customer() {
    }

    public Customer( String password, String fullName, String aadhar_no, String contact_no, String email_address, String date_of_birth, Customer_Address address) {
        this.customer_Id = 00;
        this.password = password;
        this.fullName = fullName;
        this.aadhar_no = aadhar_no;
        this.contact_no = contact_no;
        this.email_address = email_address;
        this.date_of_birth = date_of_birth;
        this.address = address;
    }

    public Customer(long customer_Id, String password, String fullName, String aadhar_no, String contact_no, String email_address, String date_of_birth, Customer_Address address) {
        this.customer_Id = customer_Id;
        this.password = password;
        this.fullName = fullName;
        this.aadhar_no = aadhar_no;
        this.contact_no = contact_no;
        this.email_address = email_address;
        this.date_of_birth = date_of_birth;
        this.address = address;
    }

    public long getCustomer_Id() {
        return customer_Id;
    }

    public void setCustomer_Id(long customer_Id) {
        this.customer_Id = customer_Id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAadhar_no() {
        return aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        this.aadhar_no = aadhar_no;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public Customer_Address getAddress() {
        return address;
    }

    public void setAddress(Customer_Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_Id=" + customer_Id +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", aadhar_no='" + aadhar_no + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", email_address='" + email_address + '\'' +
                ", date_of_birth='" + date_of_birth + '\'' +
                ", address=" + address +
                '}';
    }
}
