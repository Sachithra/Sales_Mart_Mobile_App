package com.example.myapplication.controller;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.myapplication.db.DBHandler;
import com.example.myapplication.db.SQLiteDatabaseHelper;
import com.example.myapplication.model.Customer;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class CustomerController {

    public static void insertCustomer(Context con, ArrayList<Customer> customers){
        SQLiteDatabaseHelper databaseHelper = SQLiteDatabaseHelper.getDatabaseInstance(con);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            database.beginTransaction();

            String insertAssetQuery = "insert into CUSTOMERS (customerID,customerName,customerPhone,customerAge,customerType,imageSave) values(?,?,?,?,?,?)";

            SQLiteStatement assetStatement = database.compileStatement(insertAssetQuery);

            for (Customer cus : customers) {
                DBHandler.performExecuteInsert(assetStatement, new Object[]{
                        cus.getCustomerId(),cus.getCustomerName(),cus.getCustomerPhone(),cus.getCustomerAge(),cus.getCheck_type(),cus.getImage()
                });

            }
            database.setTransactionSuccessful();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            database.endTransaction();
            databaseHelper.close();
        }
    }


}
