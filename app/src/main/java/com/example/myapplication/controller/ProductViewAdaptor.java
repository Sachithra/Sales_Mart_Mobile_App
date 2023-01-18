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

public class ProductViewAdaptor extends BaseAdapter {
    Context con;
    ArrayList<Product> product_view_arrayList;

    public ProductViewAdaptor(Context con, ArrayList<Product> product_view_arrayList) {
        this.con = con;
        this.product_view_arrayList = product_view_arrayList;
    }
    @Override
    public int getCount() {
        return this.product_view_arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return product_view_arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            LayoutInflater inflater=(LayoutInflater)con.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.customlistview3,null);
            TextView t_id=(TextView)convertView.findViewById(R.id.id_txt);
            TextView t_name=(TextView)convertView.findViewById(R.id.name_txt);
            TextView t_price=(TextView)convertView.findViewById(R.id.unit_txt);
            TextView t_stock= (TextView)convertView.findViewById(R.id.price_txt);

            Product product=product_view_arrayList.get(position);
            t_id.setText(String.valueOf(product.getProductID()));
            t_name.setText(product.getProductName());
            t_stock.setText(product.getProductUnits());
            t_price.setText(product.getPrice());

        }
        return convertView;
    }

}
