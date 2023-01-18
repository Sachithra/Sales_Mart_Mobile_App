package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.myapplication.controller.CustomerViewAndOrderAdapter;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Orders;
import java.util.ArrayList;

public class CustomerView extends AppCompatActivity {

    ListView customer_show_listview;
    TextView CusName;
    ArrayList<Customer> arrayList;
    CustomerViewAndOrderAdapter customerView;
    ArrayList<Orders>od_items= new ArrayList<>();
    private DBHandler dbHandler =new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager
                .LayoutParams.FLAG_FULLSCREEN);

        dbHandler.openDB();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_view);
        customer_show_listview=(ListView) findViewById(R.id.listview2);
        CusName=(TextView)findViewById(R.id.name_txt);
        arrayList=new ArrayList<>();
        localDataView();

        customer_show_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Customer customer=new Customer();

                String str=customer_show_listview.getAdapter().getItem(position).toString();
                customer.setCustomerName(str);
                String name=customer.getCustomerName();

                Intent intent=new Intent(getApplicationContext(),PlaceOrder.class);
                intent.putExtra("name",String.valueOf(name));
                intent.putExtra("od_details",od_items);

                startActivity(intent);
                customer_show_listview.setSelector(R.color.black);
            }
        });
    }

    private void localDataView() {
        arrayList=dbHandler.getCustomer();
        customerView=new CustomerViewAndOrderAdapter(this,arrayList);
        customer_show_listview.setAdapter(customerView);
        customerView.notifyDataSetChanged();

    }
}