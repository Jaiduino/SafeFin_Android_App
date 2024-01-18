package com.example.insuranceapp.Activity;

import static com.example.insuranceapp.Utils.GetImageFromCamera.handleImageResult;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.insuranceapp.Backend_Services.Application_Services;
import com.example.insuranceapp.Backend_Services.Category_Services;
import com.example.insuranceapp.Backend_Services.Customer_Services;
import com.example.insuranceapp.Dtos.Customer_Dto;
import com.example.insuranceapp.Dtos.Save_Application_Dto;
import com.example.insuranceapp.Model.Category;
import com.example.insuranceapp.Model.Device;
import com.example.insuranceapp.R;
import com.example.insuranceapp.Utils.Constant;
import com.example.insuranceapp.Utils.GetImageFromCamera;
import com.example.insuranceapp.Utils.ImageDialogBox;
import com.example.insuranceapp.Utils.QueryParamUtils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddApplication extends AppCompatActivity implements GetImageFromCamera.ImageCaptureListener {
TextInputLayout CustomerTextInputLayout,Loan_or_Invoice_NoTextInputLayout,Categories_TextInputLayout,Upload_Invoice_or_Loan_Image_TextInputLayout;
TextInputEditText EditCustomer_Id ,EditLoan_or_Invoice_No,EditIMEI_No,EditBrand_Name,EditModel_Name,EditDevice_Price,EditUpload_Invoice_or_Loan_Image,EditUpload_Customer_with_Device_Image;
Button Save_Button;
FloatingActionButton Upload_Invoice_or_Loan_Image_Button,Upload_Customer_with_Device_Image_Button;
RadioGroup Payment_mode_Radio_Group;
RadioButton cashRadioButton, loanRadioButton;
AutoCompleteTextView AutoCompleteCategory;
List<Category> categories = new ArrayList<>();
String payment_mode = "";
Customer_Dto cus_dto = new Customer_Dto();
    private static final int REQUEST_FIRST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_SECOND_IMAGE_CAPTURE = 2;
byte[] Invoice_or_loan_Image;
byte[] Customer_with_Device_Image;
int cat_id;
    private static final String PREFERENCES_NAME = "MyPreferences";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_application);
        initVariables();
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        Long retailer_id = sharedPreferences.getLong("retailer_ID",0);
        CustomerTextInputLayout.setHelperTextEnabled(true);
        CustomerTextInputLayout.setHelperText("Enter Customer Id to search");
        categories = getDeviceCategories();
        Upload_Invoice_or_Loan_Image_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("test","cus_dto is "+cus_dto);
                if(cus_dto.getFull_Name() !=null){
                    GetImageFromCamera.captureImage(AddApplication.this,AddApplication.this,REQUEST_FIRST_IMAGE_CAPTURE);
                }
                else {
                    generateTost_Message("Find Customer");
                }

            }
        });
        Upload_Customer_with_Device_Image_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetImageFromCamera.captureImage(AddApplication.this,AddApplication.this,REQUEST_SECOND_IMAGE_CAPTURE);
            }
        });
        CustomerTextInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customerId = EditCustomer_Id.getText().toString().trim();
                if (!customerId.isEmpty()) {
                    Log.e("test", "id " + customerId);
                    Customer_Dto temp_cus_dto = new Customer_Dto();
                    temp_cus_dto =Search_Customer(customerId);



                } else {
                    generateTost_Message("Enter Customer Id");
                }
            }
        });
        Payment_mode_Radio_Group.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.cashRadioButton) {
                Loan_or_Invoice_NoTextInputLayout.setHint("Enter Invoice No");
                Upload_Invoice_or_Loan_Image_TextInputLayout.setHint("Upload Invoice Image");
                Log.e("test", "cash");
                payment_mode = "cash";
            } else if (checkedId == R.id.loanRadioButton) {
              Loan_or_Invoice_NoTextInputLayout.setHint("Enter Loan No");
                Upload_Invoice_or_Loan_Image_TextInputLayout.setHint("Upload Loan Document Image");
                Log.e("test", "loan");
                payment_mode = "loan";
            }
        });
        ArrayAdapter<Category> adapter = new ArrayAdapter(this, R.layout.category_spinner_list, R.id.textViewCategory, categories) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext()).inflate(R.layout.category_spinner_list, parent, false);
                }

                Category category = (Category) getItem(position);
                if (category != null) {
                    TextView textViewCategoryName = convertView.findViewById(R.id.textViewCategory);


                    textViewCategoryName.setText(category.getCategory_name());
                }

                return convertView;
            }
        };
        AutoCompleteCategory.setAdapter(adapter);
        AutoCompleteCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Category selectedCategory = (Category) adapterView.getItemAtPosition(i);
                Log.e("test","cat is "+ selectedCategory.getCategory_name());
                AutoCompleteCategory.setText(selectedCategory.getCategory_name());
                cat_id = selectedCategory.getId();
            }
        });
        Save_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              Save_Application_Dto appDto = getData();
              if(appDto!=null){
                  if(cus_dto!= null){
                      Log.e("test","getdata "+appDto);
                      Log.e("test","IMAGE1 "+Invoice_or_loan_Image.toString());
                      Log.e("test","Image2"+Customer_with_Device_Image.toString());
                     // Save_Application_toDB(retailer_id,appDto,cus_dto,Invoice_or_loan_Image,Customer_with_Device_Image);
                      Long r_id = Long.valueOf(999281);
                      Save_Application_toDB(r_id,appDto,cus_dto,Invoice_or_loan_Image,Customer_with_Device_Image);
                  }else {
                      Log.e("test","nullcus_dto ");
                  }

              }
              else {
                  Log.e("test","nullappDto  ");
              }
            }
        });

    }

    private Customer_Dto ShowVerifyDetailsDialog(Context context, Customer_Dto cus_dto) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Check Customer Name ").setMessage("Customer Name :- "+cus_dto.getFull_Name());
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

return cus_dto;
    }

    private void Save_Application_toDB(Long retailer_id, Save_Application_Dto appDto, Customer_Dto cus_dto, byte[] invoice_or_loan_image, byte[] customer_with_device_image) {
        String invoice_or_loan_image_name =appDto.getInvoice_No_or_loan_Id_No()+"image";
        String customer_with_device_image_name = cus_dto.getFull_Name()+"image";
        RequestBody requestBody_image1 = RequestBody.create(MediaType.parse("image/*"), invoice_or_loan_image);
        MultipartBody.Part invoice_or_loan_image_multi = MultipartBody.Part.createFormData("invoice_loan_image", invoice_or_loan_image_name+".jpg", requestBody_image1);
        RequestBody requestBody_image2 = RequestBody.create(MediaType.parse("image/*"), invoice_or_loan_image);
        MultipartBody.Part customer_with_device_image_multi = MultipartBody.Part.createFormData("customer_with_device_image",customer_with_device_image_name+".jpg", requestBody_image2);
        String app_dto_JsonString = new Gson().toJson(appDto);
        RequestBody userJson = RequestBody.create(MediaType.parse("text/plain"), app_dto_JsonString);

        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Application_Services.class)
                .Save_Application(cus_dto.getCustomer_Id(),retailer_id,userJson,invoice_or_loan_image_multi,customer_with_device_image_multi)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                     Log.e("test","responce is " + response.body());
                     Log.e("test", String.valueOf(response.body().get("status")));
                     if(response.body().has("data")){
                         JsonObject application_Object = response.body().getAsJsonObject("data");
                         Long application_Id = application_Object.get("loan_application_Id").getAsLong();

                         JsonObject Customer_Object = application_Object.get("customer").getAsJsonObject();
                         Long customer_Id = Customer_Object.get("customer_Id").getAsLong();
                         String customer_Name = Customer_Object.get("fullName").getAsString();

                         JsonObject Device_object = application_Object.get("device").getAsJsonObject();
                         String device_model_Name = Device_object.get("device_model_name").getAsString();
                         showApplicationDetails_Alert_Box(AddApplication.this, application_Id,customer_Id,customer_Name,device_model_Name);
                     }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                    }
                });
    }

    private void showApplicationDetails_Alert_Box(Context context, Long application_id, Long customer_id, String customer_name, String device_model_name) {

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Application Save Successfully").setMessage("Application Id :- "+application_id);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    startActivity(new Intent(context, MainActivity.class));
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();




    }

    private String convertToQueryString(Save_Application_Dto appDto) {
        StringBuilder queryString = new StringBuilder();

        // Get all fields, including inherited fields
        Field[] fields = appDto.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);

                // Get the field value
                Object value = field.get(appDto);

                if (value != null) {
                    // Encode the field name and value
                    String encodedName = URLEncoder.encode(field.getName(), "UTF-8");
                    String encodedValue = URLEncoder.encode(value.toString(), "UTF-8");

                    // Append the key-value pair to the query string
                    queryString.append(encodedName)
                            .append("=")
                            .append(encodedValue)
                            .append("&");
                }
            } catch (IllegalAccessException | IllegalArgumentException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }

        // Remove the trailing "&" if there are any parameters
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }

        return queryString.toString();
    }


    private Save_Application_Dto getData() {
        Save_Application_Dto appDto = new Save_Application_Dto();
        Device device = new Device();
        device.setImei_no(EditIMEI_No.getText().toString());
        device.setDevice_brand_name(EditBrand_Name.getText().toString());
        device.setDevice_model_name(EditModel_Name.getText().toString());
        double number=0.0;
        if (!TextUtils.isEmpty(EditDevice_Price.getText())) {
            // Parse the string to a number
            number = Double.parseDouble(EditDevice_Price.getText().toString());
            // Continue with your logic
        }
        device.setDevice_price(number);
        Category category = new Category();
        category.setId(cat_id);
        category.setCategory_name(AutoCompleteCategory.getText().toString());
        device.setCategory(category);
        appDto.setPayment_mode(payment_mode);
        appDto.setInvoice_No_or_loan_Id_No(EditLoan_or_Invoice_No.getText().toString());
        appDto.setDevice(device);
        if(!device.getImei_no().isEmpty()){
            if(!device.getCategory().getCategory_name().isEmpty()){
                if(!device.getDevice_brand_name().isEmpty()){
                    if(!device.getDevice_model_name().isEmpty()){
                        if(!EditDevice_Price.getText().toString().isEmpty()){
                            if(!appDto.getPayment_mode().isEmpty()){
                                if (!appDto.getInvoice_No_or_loan_Id_No().isEmpty()){
                                    if (Invoice_or_loan_Image!=null){
                                        if(Customer_with_Device_Image!=null){
                                            return appDto;
                                        }
                                        else {
                                            generateTost_Message("Upload Customer With Device Image");
                                        }
                                    }else {
                                        generateTost_Message("Upload Invoice or Loan Image");
                                    }

                                }
                                else {
                                    generateTost_Message("Enter Invoice or Loan No");
                                }
                            }
                            else {
                                generateTost_Message("Select Device Payment Mode");
                            }
                        }
                        else {
                            generateTost_Message("Enter Device Price");
                        }
                    }else {
                        generateTost_Message("Enter Model Name");
                    }
                }else {
                    generateTost_Message("Enter Device Brand Name");
                }
            }else {
                generateTost_Message("Select Category");
            }

        }else {
            generateTost_Message("Enter Device IMEI No");
        }
        return null;
    }

    private List<Category> getDeviceCategories() {
        List<Category> categories = new ArrayList<>();
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Category_Services.class)
                .Get_All_Categories()
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        if(response.body().has("data")){
                            JsonArray array = response.body().getAsJsonArray("data");
                            for (int i = 0;i< array.size(); i++){
                                JsonObject object = array.get(i).getAsJsonObject();
                                Category cat = new Category();
                                cat.setId(object.get("category_Id").getAsInt());
                                cat.setCategory_name(object.get("category_name").getAsString());
                                categories.add(cat);
                            }
                        } else if (response.body().has("message")) {
                            generateTost_Message("Some Thing Went Wrong !!!!");
                        }


                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                    }
                });
return categories;
    }

    private Customer_Dto Search_Customer(String customerId) {
        Customer_Dto cus = new Customer_Dto();
        Long id = Long.parseLong(customerId);
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.BASE_URL)
                .build()
                .create(Customer_Services.class)
                .Get_Customer(id)
                .enqueue(new Callback<JsonObject>() {
                    @Override
                    public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                        JsonElement status = response.body().get("status");
                        if(response.body().has("data")){
                           JsonObject object = response.body().getAsJsonObject("data");
                            Log.e("test","in side sta"+status.toString());
                            Drawable endIconDrawable = getResources().getDrawable(R.drawable.baseline_check_24);
                            CustomerTextInputLayout.setEndIconDrawable(endIconDrawable);
                            CustomerTextInputLayout.setBoxStrokeColor(getResources().getColor(R.color.green));

                            cus.setCustomer_Id(object.get("customer_Id").getAsLong());
                            cus.setFull_Name(object.get("full_Name").getAsString());
                            cus.setAadhar_No(object.get( "aadhar_No").getAsString());
                            cus.setContact_No(object.get( "contact_No").getAsString());
                            cus.setEmail_Address(object.get( "email_Address").getAsString());
                            cus.setDate_of_birth(object.get("date_of_birth").getAsString());
                            cus.setFull_address(object.get( "full_address").getAsString());
                            cus.setCity(object.get("city").getAsString());
                            cus.setPincode(object.get( "pincode").getAsString());
                            cus.setState(object.get("state").getAsString());
                            cus.setCountry(object.get("country").getAsString());
                            Log.e("test","cus"+cus.toString());
                            Log.e("test","temp cus"+cus);
                            cus_dto = ShowVerifyDetailsDialog(AddApplication.this,cus);



                        }
                        else if (response.body().has("message")){
                            JsonElement message = response.body().get("message");
                            String messageString = message.toString();
                            Show_Error_Alert_Box(AddApplication.this,messageString);

                        }

                    }

                    @Override
                    public void onFailure(Call<JsonObject> call, Throwable t) {

                        Log.e("test","failed !!!");
                        Log.e("test","error"+t.getMessage());
                    }
                });
        return cus;
    }

    private void initVariables() {
        EditUpload_Invoice_or_Loan_Image = findViewById(R.id.editUpload_Invoice_or_Loan_image);
        EditUpload_Customer_with_Device_Image = findViewById(R.id.editUpload_Customer_with_Device_pic);
        Upload_Customer_with_Device_Image_Button = findViewById(R.id.upload_Customer_with_device_Image_Button);
        Upload_Invoice_or_Loan_Image_Button = findViewById(R.id.upload_invoice_or_loan_Button);
        Upload_Invoice_or_Loan_Image_TextInputLayout = findViewById(R.id.textLayoutUpload_Invoice_or_Loan_image);
        Save_Button = findViewById(R.id.buttonSave_Application);
        EditIMEI_No = findViewById(R.id.editDevice_IMEI_No);
        EditBrand_Name = findViewById(R.id.editBrand_Name);
        EditModel_Name = findViewById(R.id.editModel_Name);
        EditDevice_Price = findViewById(R.id.editDevice_Price);
        AutoCompleteCategory = findViewById(R.id.categoryAutoCompleteTextView);
        Categories_TextInputLayout = findViewById(R.id.Category_SpinnerTextInputLayout);
        Loan_or_Invoice_NoTextInputLayout = findViewById(R.id.loan_or_invoice_NoTextInputLayout);
        EditLoan_or_Invoice_No = findViewById(R.id.editLoan_or_invoice_No);
        cashRadioButton = findViewById(R.id.cashRadioButton);
        loanRadioButton = findViewById(R.id.loanRadioButton);
        Payment_mode_Radio_Group = findViewById(R.id.paymentModeRadioGroup);
        CustomerTextInputLayout = findViewById(R.id.CustomerIdTextInputLayout);
        EditCustomer_Id = findViewById(R.id.editCustomer_Id);

    }
    private void generateTost_Message(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
    private void Show_Error_Alert_Box(Context context, String message_string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Error !!").setMessage(message_string);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onImageCaptured(Bitmap imageBitmap, int requestCode) {

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_FIRST_IMAGE_CAPTURE) {
                // Handle the result for the first image capture
                handleImageResult(data, this, new GetImageFromCamera.ImageCaptureListener() {
                    @Override
                    public void onImageCaptured(Bitmap imageBitmap, int requestCode) {
                        // Handle the captured image here for the first request
                        // Example: Display the image in an ImageView
                       // ImageDialogBox.showImageDialog(AddApplication.this,imageBitmap);

                  //   boolean flag =  ImageDialogBox.showImageDialog(AddApplication.this,imageBitmap,REQUEST_FIRST_IMAGE_CAPTURE);
                        showImage(AddApplication.this,imageBitmap,REQUEST_FIRST_IMAGE_CAPTURE);

                    }
                }, requestCode);
            } else if (requestCode == REQUEST_SECOND_IMAGE_CAPTURE) {
                // Handle the result for the second image capture
                handleImageResult(data, this, new GetImageFromCamera.ImageCaptureListener() {
                    @Override
                    public void onImageCaptured(Bitmap imageBitmap, int requestCode) {
                        // Handle the captured image here for the second request
                        // Example: Display the image in an ImageView
                        showImage(AddApplication.this,imageBitmap,REQUEST_SECOND_IMAGE_CAPTURE);

                    }
                }, requestCode);
            }
        }
    }

    private void showImage(Context context, Bitmap imageBitmap, int requestImageCaptureCode) {

        // Create a custom dialog
        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.image_dialog_box);

        // Get the ImageView from the dialog layout
        ImageView imageView = dialog.findViewById(R.id.dialogImageView);
        Button SaveImageButton = dialog.findViewById(R.id.saveImageButton);
        Button CancelImageButton = dialog.findViewById(R.id.cancelImageButton);

        // Set the image resource to the ImageView
        imageView.setImageBitmap(imageBitmap);
        SaveImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (requestImageCaptureCode == REQUEST_FIRST_IMAGE_CAPTURE){
                    Invoice_or_loan_Image = convertBitmapToByteArray(imageBitmap);
                    EditUpload_Invoice_or_Loan_Image.setText( cus_dto.getFull_Name() + " Invoice 0r Loan Image.jpg") ;
                    dialog.dismiss();
                }
                if(requestImageCaptureCode == REQUEST_SECOND_IMAGE_CAPTURE){
                    Customer_with_Device_Image = convertBitmapToByteArray(imageBitmap);
                    EditUpload_Customer_with_Device_Image.setText(cus_dto.getFull_Name()+" With device image.jpg");
                    dialog.dismiss();
                }
            }
        });
        CancelImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // Make the dialog cancelable by clicking outside
        dialog.setCanceledOnTouchOutside(true);

        // Show the dialog
        dialog.show();


    }

    private byte[] convertBitmapToByteArray(Bitmap imageBitmap) {
        if (imageBitmap == null) {
            return null; // Handle the case where the Bitmap is null
        }

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Handle the exception, e.g., log it or return a default value
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception if closing the stream fails
            }
        }
    }



}