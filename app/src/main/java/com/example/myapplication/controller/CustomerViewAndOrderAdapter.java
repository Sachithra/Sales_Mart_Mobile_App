package com.example.myapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.CustomerView;
import com.example.myapplication.PlaceOrder;
import com.example.myapplication.R;
import com.example.myapplication.SplashScreen;
import com.example.myapplication.drawer_layout;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class CustomerViewAndOrderAdapter extends BaseAdapter {

    Context con;
    ArrayList<Customer> Cus_arrayList;
    public CustomerViewAndOrderAdapter(Context con, ArrayList<Customer>Cus_arrayList){
        this.con=con;
        this.Cus_arrayList=Cus_arrayList;
    }

    @Override
    public int getCount() {
        return this.Cus_arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return Cus_arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.customlistview2,null);
            TextView cus_ID=(TextView)convertView.findViewById(R.id.id_txt);
            TextView cus_name=(TextView)convertView.findViewById(R.id.name_txt);
            TextView cus_phone=(TextView)convertView.findViewById(R.id.phone_txt);
            TextView cus_age=(TextView)convertView.findViewById(R.id.age_txt);


            Customer customer=Cus_arrayList.get(position);
            cus_ID.setText(String.valueOf(customer.getCustomerId()));
            cus_name.setText(customer.getCustomerName());
            cus_phone.setText(customer.getCustomerPhone());
            cus_age.setText(customer.getCustomerAge());

        }
        return convertView;
    }


}
