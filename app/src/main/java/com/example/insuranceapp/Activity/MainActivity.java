package com.example.insuranceapp.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.insuranceapp.Backend_Services.Retailer_Services;
import com.example.insuranceapp.Model.Applications;
import com.example.insuranceapp.Model.Category;
import com.example.insuranceapp.Model.Customer;
import com.example.insuranceapp.Model.Customer_Address;
import com.example.insuranceapp.Model.Customer_Image_with_Device;
import com.example.insuranceapp.Model.Device;
import com.example.insuranceapp.Model.Invoice_or_loan_Image;
import com.example.insuranceapp.Model.Retailer;
import com.example.insuranceapp.R;
import com.example.insuranceapp.Utils.Constant;
import com.example.insuranceapp.adapters.Retailer_Application_List_Adapter;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    private static final String PREFERENCES_NAME = "MyPreferences";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Long retailer_id = sharedPreferences.getLong("retailer_ID",0);
       List<Applications> applicationsList = getRetailer_Data(retailer_id);

       Log.e("test","out"+applicationsList);


    }

    private List<Applications> getRetailer_Data(Long retailer_id) {
        Long idd = Long.valueOf(999281);
        List<Applications> applicationsList = new ArrayList<>();
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Retailer_Services.class)
                .Get_retailer_Details_By_Id(idd)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        Log.e("test","response in main "+response.body());
                        JsonObject object = response.body().getAsJsonObject("data");
                        JsonArray jsonArray = object.getAsJsonArray("application_list");


                        for (int i = 0; i< jsonArray.size();i++){
                            JsonObject obj = jsonArray.get(i).getAsJsonObject();
                            Applications applications = new Applications();
                            applications.setLoan_application_Id(obj.get("loan_application_Id").getAsLong());
                            applications.setPayment_mode(obj.get("payment_mode").getAsString());
                            applications.setInvoice_No_or_loan_Id_No(obj.get("invoice_No_or_loan_Id_No").getAsString());
                            applications.setDate_of_approved(Date.valueOf(obj.get("date_of_approved").getAsString()));
                            applications.setExpiration_of_date(Date.valueOf(obj.get("expiration_of_date").getAsString()));
                            applications.setStatus(obj.get("status").getAsString());
                            applications.setIsclaim(obj.get("isclaim").getAsBoolean());
                            Customer cus = new Customer();
                            JsonObject jsonCustomer = obj.getAsJsonObject("customer");
                            cus.setCustomer_Id(jsonCustomer.get("customer_Id").getAsLong());
                            cus.setFullName(jsonCustomer.get("fullName").getAsString());
                            cus.setPassword(jsonCustomer.get("password").getAsString());
                            cus.setAadhar_no(jsonCustomer.get("aadhar_no").getAsString());
                            cus.setContact_no(jsonCustomer.get("contact_no").getAsString());
                            cus.setEmail_address(jsonCustomer.get("email_address").getAsString());
                            cus.setDate_of_birth(jsonCustomer.get("date_of_birth").getAsString());
                            Customer_Address cus_add = new Customer_Address();
                            JsonObject  jsonCustomer_add = jsonCustomer.getAsJsonObject("address");
                            cus_add.setCustomer_address_Id(jsonCustomer_add.get("customer_address_Id").getAsInt());
                            cus_add.setFull_address(jsonCustomer_add.get("full_address").getAsString());
                            cus_add.setCity(jsonCustomer_add.get("city").getAsString());
                            cus_add.setPincode(jsonCustomer_add.get("pincode").getAsString());
                            cus_add.setState(jsonCustomer_add.get("state").getAsString());
                            cus_add.setCountry(jsonCustomer_add.get("country").getAsString());
                            cus.setAddress(cus_add);
                            applications.setCustomer(cus);
                            Device d = new Device();
                            JsonObject jsonDevice = obj.getAsJsonObject("device");
                            d.setDevice_details_Id(jsonDevice.get("device_details_Id").getAsInt());
                            d.setImei_no(jsonDevice.get("imei_no").getAsString());
                            d.setDevice_brand_name(jsonDevice.get("device_brand_name").getAsString());
                            d.setDevice_model_name(jsonDevice.get("device_model_name").getAsString());
                            d.setDevice_price(jsonDevice.get("device_price").getAsDouble());
                            JsonObject JsonCategory = jsonDevice.getAsJsonObject("category");
                            Category cat = new Category();
                            cat.setId(JsonCategory.get("category_Id").getAsInt());
                            cat.setCategory_name(JsonCategory.get("category_name").getAsString());
                            d.setCategory(cat);
                            applications.setDevice(d);
                            Invoice_or_loan_Image image1 = new Invoice_or_loan_Image();
                            JsonObject jsonImage1 = obj.getAsJsonObject("invoice_or_loan_image");
                            image1.setId(jsonImage1.get("inv_or_lon_image_id").getAsLong());
                            image1.setImage_name(jsonImage1.get("image_name").getAsString());
                            byte[] data = Base64.getDecoder().decode(jsonImage1.get("data").getAsString());
                            image1.setData(data);
                            applications.setInvoice_or_loan_image(image1);
                            Customer_Image_with_Device image2 = new Customer_Image_with_Device();
                            JsonObject jsonImage2 = obj.getAsJsonObject("customer_Image_with_Device");
                            image2.setId(jsonImage2.get("cus_with_dev_image_id").getAsLong());
                            byte[] data2 = Base64.getDecoder().decode(jsonImage2.get("data").getAsString());
                            image2.setData(data2);
                            image2.setImage_name(jsonImage2.get("name").getAsString());
                            applications.setCustomer_image_with_device(image2);
                            applicationsList.add(applications);
                        }
                           if(!applicationsList.isEmpty()){
                               Log.d("test","app is"+applicationsList.toString());
                               updateUI(applicationsList);
                           }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                        generateTost_Message("SomeThing Went Wrong!!!");
                    }
                });
        Log.e("test","app "+applicationsList);
        return applicationsList;
    }

    private void updateUI(List<Applications> applicationsList) {
        RecyclerView recyclerView= findViewById(R.id.retailer_applications_recyclerView);
        Retailer_Application_List_Adapter adapter = new Retailer_Application_List_Adapter(this,applicationsList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_application:
                startActivity(new Intent(MainActivity.this, AddApplication.class));
                return true;


            case R.id.add_customer:
                startActivity(new Intent(MainActivity.this, AddCustomer.class));
        }
        return false;
    }
    private void generateTost_Message(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}