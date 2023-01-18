package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.myapplication.controller.CustomerViewOnlyAdapter;
import com.example.myapplication.controller.ProductViewAdaptor;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class ViewCustomersOnly extends AppCompatActivity {
    ListView cus_view_listview;
    ArrayList<Customer> customer_view_arrayLists;
    CustomerViewOnlyAdapter cus_view;
    private DBHandler dbHandler = new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dbHandler.openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customers_only);

        cus_view_listview = (ListView) findViewById(R.id.cus_view_listview);
        customer_view_arrayLists = new ArrayList<>();
        localDataView();
    }

    private void localDataView() {
        customer_view_arrayLists = dbHandler.getCustomer();
        cus_view = new CustomerViewOnlyAdapter(this, customer_view_arrayLists);
        cus_view_listview.setAdapter(cus_view);
        cus_view.notifyDataSetChanged();
    }
}