package com.example.myapplication.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class CustomerViewOnlyAdapter extends BaseAdapter {


    Context con;
    ArrayList<Customer> customer_view_arrayList;

    public CustomerViewOnlyAdapter(Context con, ArrayList<Customer> customer_view_arrayList) {
        this.con = con;
        this.customer_view_arrayList = customer_view_arrayList;
    }
    @Override
    public int getCount() {
        return this.customer_view_arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return customer_view_arrayList.get(position);
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
            TextView txt_id=(TextView)convertView.findViewById(R.id.id_txt);
            TextView txt_name=(TextView)convertView.findViewById(R.id.name_txt);
            TextView txt_phone=(TextView)convertView.findViewById(R.id.phone_txt);
            TextView txt_age= (TextView)convertView.findViewById(R.id.age_txt);

            Customer customer=customer_view_arrayList.get(position);
            txt_id.setText(String.valueOf(customer.getCustomerId()));
            txt_name.setText(customer.getCustomerName());
            txt_phone.setText(customer.getCustomerPhone());
            txt_age.setText(customer.getCustomerAge());



        }
        return convertView;
    }

}
