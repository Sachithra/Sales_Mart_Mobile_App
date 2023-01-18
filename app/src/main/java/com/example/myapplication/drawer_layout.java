package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.example.myapplication.db.DBHandler;
import com.google.android.material.navigation.NavigationView;

public class drawer_layout extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DBHandler dbHandler=new DBHandler(this);



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_fragment()).commit();
                break;

            case R.id.customer:

                Intent intent = new Intent(this,AddCustomer.class);
                startActivity(intent);
                break;
            case R.id.product:
                Intent intent1 = new Intent(this,AddProduct.class);
                startActivity(intent1);
                break;

            case R.id.order:
                Intent intent2 = new Intent(this,CustomerView.class);
                startActivity(intent2);
                break;

            case R.id.viewCustomer:
                Intent intent3 = new Intent(this,ViewCustomersOnly.class);
                startActivity(intent3);
                break;
            case R.id.viewProduct:
                Intent intent4 = new Intent(this,ViewProducs.class);
                startActivity(intent4);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);



        dbHandler.openDB();

        drawerLayout=findViewById(R.id.nav_view);

        NavigationView navigationView=findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar=findViewById(R.id.toolbar);
        ActionBarDrawerToggle actionBarDrawerToggle=new ActionBarDrawerToggle(this,
                drawerLayout,toolbar,R.string.open_nav,R.string.close_nav);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home_fragment()).commit();


    }

}