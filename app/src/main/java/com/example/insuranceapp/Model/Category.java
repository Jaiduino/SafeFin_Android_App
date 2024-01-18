package com.example.insuranceapp.Model;

import com.google.gson.annotations.SerializedName;

public class Category {
    @SerializedName("category_Id")
    private int id;
    @SerializedName("category_name")
    private String category_name;

    public Category() {
    }

    public Category(int id, String category_name) {
        this.id = id;
        this.category_name = category_name;
    }
    public Category( String category_name) {
        this.id = 0;
        this.category_name = category_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}
