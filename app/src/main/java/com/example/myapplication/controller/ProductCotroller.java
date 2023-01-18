package com.example.myapplication.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.myapplication.db.DBHandler;
import com.example.myapplication.db.SQLiteDatabaseHelper;
import com.example.myapplication.model.Product;

import java.util.ArrayList;

public class ProductCotroller {

    public static void insertProducts(Context con, ArrayList<Product> product){
        SQLiteDatabaseHelper databaseHelper = SQLiteDatabaseHelper.getDatabaseInstance(con);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        try {
            database.beginTransaction();

            String insertAssetQuery = "insert into PRODUCT (productID,productName,productUnit,productPrice,displayOrStore) values(?,?,?,?,?)";

            SQLiteStatement assetStatement = database.compileStatement(insertAssetQuery);

            for (Product pro : product) {
                DBHandler.performExecuteInsert(assetStatement, new Object[]{
                    pro.getProductID(),pro.getProductName(),pro.getProductUnits(),pro.getPrice(),pro.getStore_or_not()
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
