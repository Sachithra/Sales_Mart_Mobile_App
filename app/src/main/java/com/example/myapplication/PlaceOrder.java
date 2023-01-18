package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.controller.OrderViewAdptor;
import com.example.myapplication.controller.PlaceQuantity;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Orders;
import com.example.myapplication.model.Product;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class PlaceOrder extends AppCompatActivity {

    String customer_name;
    TextView live_time;
    ListView listview;
    TextView customerName;
    String currentDateAndTime;
    ArrayList<Product> productArrayList;
    PlaceQuantity myAdappter;
    ArrayList<Orders>od_items= new ArrayList<>();

    private Button completeOrder;

    private DBHandler dbHandler =new DBHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        dbHandler.openDB();
        setContentView(R.layout.activity_order);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            customer_name = extras.getString("name");
            customerName=(TextView) findViewById(R.id.customerName);
            customerName.getText().toString();
            customerName.setText(customer_name);
            od_items = (ArrayList<Orders>) getIntent().getSerializableExtra("od_details");

            }

            listview=(ListView) findViewById(R.id.listview);
            live_time=(TextView)findViewById(R.id.live_time);
            completeOrder=(Button)findViewById(R.id.button);

            localDataView();

            DateFormat df = new SimpleDateFormat("KK:mm:ss a, dd/MM/yyyy",
                Locale.getDefault());//date and time

            currentDateAndTime = df.format(new Date());
            productArrayList=new ArrayList<>();

            live_time.setText(currentDateAndTime);

            completeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            myAdappter=new PlaceQuantity(PlaceOrder.this,productArrayList,od_items);
            listview.setAdapter(myAdappter);
            myAdappter.notifyDataSetChanged();


            Intent intent = new Intent(PlaceOrder.this, CompleteOrder.class);
            intent.putExtra("od_details", od_items);
            intent.putExtra("name",customer_name);
            intent.putExtra("date_and_time", currentDateAndTime);
            startActivity(intent);

            }
         });
        }
            private void localDataView(){
            productArrayList=dbHandler.getAllData();
            myAdappter=new PlaceQuantity(this,productArrayList,od_items );
            listview.setAdapter(myAdappter);
            myAdappter.notifyDataSetChanged();

        }

    @Override
    public void onBackPressed() {
        Intent intent =new Intent(PlaceOrder.this,drawer_layout.class);
        startActivity(intent);
    }
}