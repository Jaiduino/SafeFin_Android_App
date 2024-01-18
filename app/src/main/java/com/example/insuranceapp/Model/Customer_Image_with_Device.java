package com.example.insuranceapp.Model;

public class Customer_Image_with_Device {
    private Long id;
    private String image_name;
    private byte[] data;

    public Customer_Image_with_Device() {
    }

    public Customer_Image_with_Device(Long id, String image_name, byte[] data) {
        this.id = id;
        this.image_name = image_name;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
