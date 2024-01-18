package com.example.insuranceapp.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insuranceapp.Backend_Services.Customer_Services;
import com.example.insuranceapp.Model.Customer;
import com.example.insuranceapp.Model.Customer_Address;
import com.example.insuranceapp.R;
import com.example.insuranceapp.Utils.Constant;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCustomer extends AppCompatActivity {
    TextInputEditText dateEditText,EditFullName,EditPassword,EditConfirmPassword,EditEmail,EditContactNo,EditAadharNo,EditFullAddress,EditCity,EditPincode,EditState,EditCountry;
     TextInputLayout PasswordTextInputLayout;
    Button saveButton;
    TextView customer_Detail_TextView;
    CardView customer_Details_CardView;
    ScrollView customer_Details_ScrollView;
    LinearProgressIndicator linearProgressIndicator;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        Log.e("test","oncreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custmer_details);
        initial_all_verialbles();
linearProgressIndicator.hide();
     getDate();
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Customer cus = getCustomer_Info();
                if(cus !=null){
                    linearProgressIndicator.show();
                    Log.e("test","cus is"+cus.toString());
                    SaveCustomer_Info_To_Server(cus);
                }
                else {
                    Log.e("test","cus is null");
                }

            }
        });

    }

    private void SaveCustomer_Info_To_Server(Customer cus) {
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Customer_Services.class)
                .Save_Customer(cus)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonElement status = response.body().get("status");
                        Log.e("test","cus "+status);
                        String st = status.getAsString();
                        if (st.equals("success")){
                            if(response.body().has("data")){
                                JsonObject object = response.body().getAsJsonObject("data");
                                JsonElement id = object.get("customer_Id");
                                String id_string = id.toString();
                                linearProgressIndicator.hide();
                                showDialogBox(AddCustomer.this,id_string);
                            }else {
                                generateTost_Message("Some Thing went Wrong !!!");
                            }

                        }else
                        if(st.equals("error")){
                            if(response.body().has("message")){
                                JsonElement message = response.body().get("message");
                                String message_String = message.toString();
                                Show_Error_Alert_Box(AddCustomer.this,message_String);

                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                    }
                });
    }

    private void Show_Error_Alert_Box(Context context, String message_string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error !!").setMessage(message_string);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              linearProgressIndicator.hide();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showDialogBox(Context context, String id_string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Info Save Successfully").setMessage("Customer ID :- "+id_string);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                startActivity(new Intent(context, AddApplication.class));
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }


    private void initial_all_verialbles() {
        linearProgressIndicator = findViewById(R.id.linearprogressbar);
        saveButton = findViewById(R.id.buttonSave);
        dateEditText = findViewById(R.id.editTextDate);
        customer_Detail_TextView = findViewById(R.id.customer_textview);
        customer_Details_ScrollView = findViewById(R.id.cardViewScrollView);
        customer_Details_CardView = findViewById(R.id.cardViewCustomer_Details);
        EditFullName = findViewById(R.id.editfullName);
        EditPassword = findViewById(R.id.editPassword);
        EditConfirmPassword = findViewById(R.id.editConfirmPassword);
        EditEmail = findViewById(R.id.editEmail);
        EditContactNo = findViewById(R.id.editContactNo);
        EditAadharNo = findViewById(R.id.editAadharNo);
        EditFullAddress = findViewById(R.id.editfullAddress);
        EditCity = findViewById(R.id.editCity);
        EditPincode = findViewById(R.id.editPincode);
        EditState = findViewById(R.id.editState);
        EditCountry = findViewById(R.id.editCountry);
        PasswordTextInputLayout = findViewById(R.id.passwordTextInputLayout);

    }

    private void getDate() {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select Date")
                .build();
        datePicker.addOnPositiveButtonClickListener(selection -> {
            String formattedDate = formateDate(selection);
            dateEditText.setText(formattedDate);
        });
        dateEditText.setOnClickListener(view -> {
            // Show the date picker
            datePicker.show(getSupportFragmentManager(), "DATE_PICKER");
        });
    }

    private String formateDate(Long selection) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return simpleDateFormat.format(selection);
    }
    private Customer getCustomer_Info() {
        Customer cus = new Customer();
        cus.setFullName(EditFullName.getText().toString());
        if (EditPassword.getText().toString().equals(EditConfirmPassword.getText().toString())){

            cus.setPassword(EditPassword.getText().toString());
            cus.setEmail_address(EditEmail.getText().toString());
            cus.setContact_no(EditContactNo.getText().toString());
            cus.setAadhar_no(EditAadharNo.getText().toString());
            cus.setDate_of_birth(dateEditText.getText().toString());
            Customer_Address customer_address = new Customer_Address();
            customer_address.setFull_address(EditFullAddress.getText().toString());
            customer_address.setCity(EditCity.getText().toString());
            customer_address.setPincode(EditPincode.getText().toString());
            customer_address.setState(EditState.getText().toString());
            customer_address.setCountry(EditCountry.getText().toString());
            cus.setAddress(customer_address);
            if (!cus.getFullName().isEmpty()){
                if (!cus.getPassword().isEmpty()){
                    if (!cus.getEmail_address().isEmpty()){
                        if (!cus.getContact_no().isEmpty()){

                                if (!cus.getAddress().getFull_address().isEmpty()){
                                    if(!cus.getAddress().getCity().isEmpty()){
                                        if(!cus.getAddress().getPincode().isEmpty()){
                                            if(!cus.getAddress().getState().isEmpty()){
                                                if(!cus.getAddress().getCountry().isEmpty()){
                                                    if (!cus.getAadhar_no().isEmpty()){
                                                        return cus;
                                                    }
                                                    else {
                                                        generateTost_Message("Enter Aadhar No");
                                                    }
                                                }else {
                                                    generateTost_Message("Enter Country");
                                                }
                                            }else {
                                                generateTost_Message("Enter State");
                                            }
                                        }else {
                                         generateTost_Message("Enter Pincode");
                                        }
                                    }else {
                                        generateTost_Message("Enter City");
                                    }
                                }else {
                                    generateTost_Message("Enter Full Address");
                                }
                        }else {
                            generateTost_Message("Enter Contact No");
                        }
                    }else {
                        generateTost_Message("Enter Email Address");
                    }

                }else {
                    generateTost_Message("Enter Password");
                }
            }else {
                generateTost_Message("Enter Full Name");
            }

            return null;

        }
      else {

            generateTost_Message("Password Not matched!!");


        }
   return null;
    }
    private void generateTost_Message(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

}
