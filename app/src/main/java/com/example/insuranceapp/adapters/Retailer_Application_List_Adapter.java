package com.example.insuranceapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insuranceapp.Model.Applications;
import com.example.insuranceapp.R;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class Retailer_Application_List_Adapter extends RecyclerView.Adapter<Retailer_Application_List_Adapter.MyViewHolder> {

    public Context context;
    public List<Applications> applicationsList = new ArrayList<>();

    public Retailer_Application_List_Adapter() {
    }

    public Retailer_Application_List_Adapter(Context context, List<Applications> applicationsList) {
        this.context = context;
        this.applicationsList = applicationsList;
        Log.e("test","list in adapter is"+applicationsList);
        for (Applications application : applicationsList) {
            Log.e("test", "loan_application_Id: " + application.getLoan_application_Id());

            // ... log other properties
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(context).inflate(R.layout.retailer_application_layout,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Applications application = applicationsList.get(position);
        String application_id = String.valueOf(application.getLoan_application_Id());
        holder.Application_Id_TextView.setText(application_id);
        holder.Device_Name_TextView.setText(application.getDevice().getDevice_model_name());
        holder.Customer_Name_TextView.setText(application.getCustomer().getFullName());
    }

    @Override
    public int getItemCount() {
        if(applicationsList.isEmpty()){
            return 0;
        }
        return applicationsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Application_Id_TextView, Customer_Name_TextView, Device_Name_TextView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Application_Id_TextView = itemView.findViewById(R.id.application_id_TextView);
            Customer_Name_TextView = itemView.findViewById(R.id.customer_name_TextView);
            Device_Name_TextView = itemView.findViewById(R.id.device_name_TextView);
        }
    }
}
