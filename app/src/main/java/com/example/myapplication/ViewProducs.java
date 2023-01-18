package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.myapplication.controller.ProductViewAdaptor;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class ViewProducs extends AppCompatActivity {

    ListView product_view_listview;
    ArrayList<Product> product_view_arrayList;
    ProductViewAdaptor pro_view;
    private DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dbHandler.openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_producs);

        product_view_listview = (ListView) findViewById(R.id.product_view_listview);
        product_view_arrayList = new ArrayList<>();
        localDataView();
    }
    private void localDataView() {
        product_view_arrayList = dbHandler.getProduct();
        pro_view = new ProductViewAdaptor(this, product_view_arrayList);
        product_view_listview.setAdapter(pro_view);
        pro_view.notifyDataSetChanged();
    }
}