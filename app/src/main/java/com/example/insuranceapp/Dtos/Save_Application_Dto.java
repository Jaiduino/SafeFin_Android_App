package com.example.insuranceapp.Dtos;

import com.example.insuranceapp.Model.Device;
import com.google.gson.annotations.SerializedName;

public class Save_Application_Dto {
    @SerializedName("payment_mode")
    private String payment_mode;
    @SerializedName("invoice_No_or_loan_Id_No")
    private String invoice_No_or_loan_Id_No;
    @SerializedName("device")
    private Device device;

    public Save_Application_Dto() {
    }

    public Save_Application_Dto(String payment_mode, String invoice_No_or_loan_Id_No, Device device) {
        this.payment_mode = payment_mode;
        this.invoice_No_or_loan_Id_No = invoice_No_or_loan_Id_No;
        this.device = device;
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

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Save_Application_Dto{" +
                "payment_mode='" + payment_mode + '\'' +
                ", invoice_No_or_loan_Id_No='" + invoice_No_or_loan_Id_No + '\'' +
                ", device=" + device +
                '}';
    }
}
