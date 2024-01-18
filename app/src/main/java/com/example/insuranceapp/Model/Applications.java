package com.example.insuranceapp.Model;

import java.sql.Date;

public class Applications {

    private long loan_application_Id;
    private String payment_mode;
    private String invoice_No_or_loan_Id_No;
    private String status;
    private Date date_of_approved;
    private Date expiration_of_date;
    private boolean isclaim;
    private Device device;
    private Customer customer;
    private Invoice_or_loan_Image invoice_or_loan_image;
    private Customer_Image_with_Device customer_image_with_device;

    public Applications() {

    }

    public Applications(long loan_application_Id, String payment_mode, String invoice_No_or_loan_Id_No, String status, Date date_of_approved, Date expiration_of_date, boolean isclaim, Device device, Customer customer, Invoice_or_loan_Image invoice_or_loan_image, Customer_Image_with_Device customer_image_with_device) {
        this.loan_application_Id = loan_application_Id;
        this.payment_mode = payment_mode;
        this.invoice_No_or_loan_Id_No = invoice_No_or_loan_Id_No;
        this.status = status;
        this.date_of_approved = date_of_approved;
        this.expiration_of_date = expiration_of_date;
        this.isclaim = isclaim;
        this.device = device;
        this.customer = customer;
        this.invoice_or_loan_image = invoice_or_loan_image;
        this.customer_image_with_device = customer_image_with_device;
    }

    public long getLoan_application_Id() {
        return loan_application_Id;
    }

    public void setLoan_application_Id(long loan_application_Id) {
        this.loan_application_Id = loan_application_Id;
    }

    public String getPayment_mode() {
        return payment_mode;
    }

    public void setPayment_mode(String payment_mode) {
        this.payment_mode = payment_mode;
    }

    public String getInvoice_No_or_loan_Id_No() {
        return invoice_No_or_loan_Id_No;
    }

    public void setInvoice_No_or_loan_Id_No(String invoice_No_or_loan_Id_No) {
        this.invoice_No_or_loan_Id_No = invoice_No_or_loan_Id_No;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_of_approved() {
        return date_of_approved;
    }

    public void setDate_of_approved(Date date_of_approved) {
        this.date_of_approved = date_of_approved;
    }

    public Date getExpiration_of_date() {
        return expiration_of_date;
    }

    public void setExpiration_of_date(Date expiration_of_date) {
        this.expiration_of_date = expiration_of_date;
    }

    public boolean isIsclaim() {
        return isclaim;
    }

    public void setIsclaim(boolean isclaim) {
        this.isclaim = isclaim;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice_or_loan_Image getInvoice_or_loan_image() {
        return invoice_or_loan_image;
    }

    public void setInvoice_or_loan_image(Invoice_or_loan_Image invoice_or_loan_image) {
        this.invoice_or_loan_image = invoice_or_loan_image;
    }

    public Customer_Image_with_Device getCustomer_image_with_device() {
        return customer_image_with_device;
    }

    public void setCustomer_image_with_device(Customer_Image_with_Device customer_image_with_device) {
        this.customer_image_with_device = customer_image_with_device;
    }

    @Override
    public String toString() {
        return "Applications{" +
                "loan_application_Id=" + loan_application_Id +
                ", payment_mode='" + payment_mode + '\'' +
                ", invoice_No_or_loan_Id_No='" + invoice_No_or_loan_Id_No + '\'' +
                ", status='" + status + '\'' +
                ", date_of_approved=" + date_of_approved +
                ", expiration_of_date=" + expiration_of_date +
                ", isclaim=" + isclaim +
                ", device=" + device +
                ", customer=" + customer +
                ", invoice_or_loan_image=" + invoice_or_loan_image +
                ", customer_image_with_device=" + customer_image_with_device +
                '}';
    }
}
