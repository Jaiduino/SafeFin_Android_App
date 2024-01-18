package com.example.insuranceapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insuranceapp.Model.Customer;
import com.example.insuranceapp.R;

import java.util.List;

public class CustomerListAdapter extends RecyclerView.Adapter<CustomerListAdapter.MyViewHolder>{
    private Context context;
    private List<Customer> customersList;


    public CustomerListAdapter(Context context, List<Customer> customersList) {
        this.context = context;
        this.customersList = customersList;
    }

    @NonNull
    @Override
    public CustomerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.customer_list, parent, false);
           return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Customer customer= customersList.get(position);

    }


    @Override
    public int getItemCount() {
        return customersList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView customer_name, customer_id, customer_email, customer_phone;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            customer_name= itemView.findViewById(R.id.customer_name);
          //  customer_id= itemView.findViewById(R.id.customer_id);
            customer_email= itemView.findViewById(R.id.email_id);
            customer_phone=itemView.findViewById(R.id.phone_no);

        }
    }
}
