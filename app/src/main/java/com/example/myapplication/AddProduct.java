package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.myapplication.controller.ProductCotroller;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AddProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
    private Button add_btn;
    private EditText idTV,nameTV,unitTV,priceTV;
    Spinner display_or_not;
    String spring_values;
    private DBHandler dbHandler =new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dbHandler.openDB();
        setContentView(R.layout.activity_add_product);
        add_btn=(Button) findViewById(R.id.add_btn);
        idTV=(EditText) findViewById(R.id.idTV);
        nameTV=(EditText) findViewById(R.id.nameTV);
        unitTV=(EditText) findViewById(R.id.unitTV);
        priceTV=(EditText) findViewById(R.id.priceTV);
        display_or_not=(Spinner)findViewById(R.id.spinner);

        display_or_not.setOnItemSelectedListener(this);
        loadSpinner();

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int productID=Integer.parseInt(idTV.getText().toString());
                String ProductName=nameTV.getText().toString();
                String ProductUnit=unitTV.getText().toString();
                String ProductPrice=priceTV.getText().toString();

                ArrayList<Product> products=new ArrayList<>();
                Product tmp=new Product();
                tmp.setProductID(productID);
                tmp.setProductName(ProductName);
                tmp.setProductUnits(ProductUnit);
                tmp.setPrice(ProductPrice);
                tmp.setStore_or_not(spring_values);

                products.add(tmp);

                ProductCotroller.insertProducts(AddProduct.this,products);
            }
        });

    }

    public void loadSpinner(){

        List<String> Spinner_values=dbHandler.getProductType();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.
                simple_spinner_item, Spinner_values);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        display_or_not.setAdapter(dataAdapter);

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spring_values = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}