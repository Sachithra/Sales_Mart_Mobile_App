package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.controller.OrderViewAdptor;
import com.example.myapplication.controller.PlaceQuantity;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Orders;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class CompleteOrder extends AppCompatActivity {
    TextView date_and_time_txt;
    ListView soredItem;
    TextView txt_cusName;
    TextView txtTotAmount;
    ArrayList<Orders>getStoreList;
    String  customer_name;
    String date_and_time;
    double tot_amt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_order);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

            soredItem = findViewById(R.id.listStore);
            txtTotAmount= findViewById(R.id.tot_price);
            getStoreList = (ArrayList<Orders>) getIntent().getSerializableExtra("od_details");

            Bundle extras = getIntent().getExtras();
            if (extras != null) {
              customer_name = extras.getString("name");
              txt_cusName=(TextView)findViewById(R.id.cus_name);
              txt_cusName.getText().toString();
              txt_cusName.setText(customer_name);

              date_and_time=extras.getString("date_and_time");
              date_and_time_txt=(TextView)findViewById(R.id.date_and_time);
              date_and_time_txt.getText().toString();
              date_and_time_txt.setText(date_and_time);

           }

            OrderViewAdptor ordersArrayAdapter = new OrderViewAdptor(this, getStoreList);
            ListView ordersListView = findViewById(R.id.listStore);

            for(Orders se_item :getStoreList){
            int qty = Integer.parseInt(se_item.getPoductQty());
            double price = Double.parseDouble(se_item.getProductPrice());
            double item_val = qty * price;
            tot_amt =tot_amt+item_val;
            }
            txtTotAmount.setText(tot_amt+"");
            ordersListView.setAdapter(ordersArrayAdapter);
         }

            @Override
            public void onBackPressed() {
            Intent intent = new Intent(CompleteOrder.this, PlaceOrder.class);
            intent.putExtra("od_details", getStoreList);
            intent.putExtra("name",customer_name);
            startActivity(intent);
        }
    }