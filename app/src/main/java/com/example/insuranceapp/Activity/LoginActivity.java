package com.example.insuranceapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.insuranceapp.Backend_Services.Retailer_Services;
import com.example.insuranceapp.Model.Credential;
import com.example.insuranceapp.R;
import com.example.insuranceapp.Utils.Constant;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Button login;
    EditText editRetailer_Id, editRetailer_Password;
    private static final String PREFERENCES_NAME = "MyPreferences";
;    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editRetailer_Id = findViewById(R.id.edit_Retailer_Id);
        editRetailer_Password = findViewById(R.id.edit_Retailer_password);
        login=findViewById(R.id.btn_login);
        login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        if (!editRetailer_Id.getText().toString().isEmpty()
//                                && !editRetailer_Password.getText().toString().isEmpty()) {
//                            Credential cre = new Credential();
//                            cre.setRetailer_Id(editRetailer_Id.getText().toString());
//                            cre.setPassword(editRetailer_Password.getText().toString());
//                          //  Credential credential = validate();
//                            if (cre != null) {
//                                Log.e("test","cre is "+cre.toString());
//                                LoginRetailer(cre);
//
//                            }
//                        }
//                        else {
//                            generateTost_Message("Enter ID And Password !!!");
//                        }
                    }
                });
    }

    private void LoginRetailer(Credential credential) {
    Log.e("test","inside login"+credential.toString());
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Retailer_Services.class)
                .Login_Retailer(credential)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                       JsonElement status = response.body().get("status");
                        if(response.body().has("data")){
                            generateTost_Message("Login Successfully !!!");
                            JsonElement id = response.body().get("data");
                            Long Long_id = id.getAsLong();
                            Log.e("test", "response id "+ id);
                          if (SaveId(Long_id)) {
                              startActivity(new Intent(LoginActivity.this, MainActivity.class));
                              finish();
                          }
                        }else
                            if(response.body().has("message")){
                                JsonElement message = response.body().get("message");
                                generateTost_Message(String.valueOf(message));


                        }



                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                    }
                });

    }

    private boolean SaveId(Long long_id) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("retailer_ID",long_id);
       return editor.commit();
    }


    private void generateTost_Message(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


}